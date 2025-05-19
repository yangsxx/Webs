package top.yangsc.services;

import com.baomidou.mybatisplus.extension.service.IService;
import top.yangsc.base.ResultData;
import top.yangsc.base.pojo.ClipboardHistory;
import top.yangsc.config.PageResult;
import top.yangsc.controller.bean.vo.ClipboardPageVO;
import top.yangsc.controller.bean.vo.CreateClipboardVO;
import top.yangsc.controller.bean.vo.resp.ClipboardRespVO;

public interface ClipboardHistoryService extends IService<ClipboardHistory> {
    ResultData<String> createClipboard(CreateClipboardVO createClipboardVO);

    PageResult<ClipboardRespVO> getClipboard(ClipboardPageVO clipboardPageVO);
}
