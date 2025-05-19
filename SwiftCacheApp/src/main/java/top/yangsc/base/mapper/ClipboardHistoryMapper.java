package top.yangsc.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.yangsc.base.pojo.ClipboardHistory;
import top.yangsc.controller.bean.vo.ClipboardPageVO;
import top.yangsc.controller.bean.vo.resp.ClipboardRespVO;


import java.util.List;

@Mapper
public interface ClipboardHistoryMapper extends BaseMapper<ClipboardHistory> {
    List<ClipboardRespVO> getClipboard(ClipboardPageVO clipboardPageVO);

}
