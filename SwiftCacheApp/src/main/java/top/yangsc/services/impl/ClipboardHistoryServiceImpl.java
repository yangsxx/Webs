package top.yangsc.services.impl;

import cn.hutool.crypto.digest.MD5;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.yangsc.Schedule.mq.KeysDTO;
import top.yangsc.Schedule.mq.Producer;
import top.yangsc.base.ResultData;
import top.yangsc.base.mapper.ClipboardHistoryMapper;
import top.yangsc.base.mapper.ClipboardValuesMapper;
import top.yangsc.base.pojo.ClipboardHistory;
import top.yangsc.cache.FindUserWithCache;
import top.yangsc.config.PageResult;
import top.yangsc.controller.bean.vo.ClipboardPageVO;
import top.yangsc.controller.bean.vo.CreateClipboardVO;
import top.yangsc.controller.bean.vo.resp.ClipboardRespVO;
import top.yangsc.services.ClipboardHistoryService;
import top.yangsc.tools.TimestampUtil;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Service
public class ClipboardHistoryServiceImpl extends ServiceImpl<ClipboardHistoryMapper, ClipboardHistory> implements ClipboardHistoryService {

    @Resource
    private ClipboardHistoryMapper clipboardHistoryMapper;

    @Resource
    private ClipboardValuesMapper clipboardValuesMapper;

    @Resource
    private Producer producer;

    MD5 md5 = MD5.create();

    @Override
    public ResultData<String> createClipboard(CreateClipboardVO createClipboardVO) {
//      查重插入，并获取id
        Map<String,Object> resultMap = clipboardValuesMapper.insertOne(createClipboardVO.getContent());
        Long  l = (Long) resultMap.get("id");
        Boolean pertain = (Boolean) resultMap.get("pertain");


        if ( l == null){
            throw  new RuntimeException("数据库操作失败，请联系管理员");
        }
        ClipboardHistory clipboardHistory = new ClipboardHistory();
        clipboardHistory.setSystem(createClipboardVO.getSystemTag());
        clipboardHistory.setTagName(createClipboardVO.getTagName());
        clipboardHistory.setValueId(l);


        clipboardHistoryMapper.insert(clipboardHistory);

        if (pertain){
            aiTask(clipboardHistory.getId(),createClipboardVO.getContent());
        }


        return ResultData.ok("记录完成");
    }

    @Override
    public PageResult<ClipboardRespVO> getClipboard(ClipboardPageVO clipboardPageVO) {
        clipboardPageVO.setOffset((clipboardPageVO.getPageNum()-1) * clipboardPageVO.getPageSize());
        List<ClipboardRespVO> clipboardPageRespVO = clipboardHistoryMapper.getClipboard(clipboardPageVO);
        clipboardPageRespVO.forEach(clipboardRespVO -> {
            clipboardRespVO.setUserName(FindUserWithCache.findUserById(clipboardRespVO.getUserId()).getUserName());
            clipboardRespVO.setCreateTime(TimestampUtil.format(clipboardRespVO.getCreatedAt()));
        });
        Long l = clipboardValuesMapper.selectCount(new LambdaQueryWrapper<>());
        return PageResult.init(l,clipboardPageVO.getPageSize(),clipboardPageVO.getPageNum(),clipboardPageRespVO);
    }

    private void aiTask(Long clipboardId ,String content){
        KeysDTO keysDTO =new KeysDTO();
        keysDTO.setClipboardValueId(clipboardId);
        keysDTO.setValue(content);
        producer.sendAiTask(JSONUtil.toJsonStr(keysDTO));
    }
}
