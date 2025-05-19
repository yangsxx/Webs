package top.yangsc.controller;



import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import top.yangsc.base.ResultData;
import top.yangsc.config.PageResult;
import top.yangsc.controller.bean.vo.ClipboardPageVO;
import top.yangsc.controller.bean.vo.CreateClipboardVO;
import top.yangsc.controller.bean.vo.resp.ClipboardRespVO;
import top.yangsc.services.ClipboardHistoryService;

import javax.annotation.Resource;


@RestController
@RequestMapping("/clipboard")
@Tag(name = "剪贴板", description = "剪贴板相关接口")
public class ClipboardController {
    @Resource

    private ClipboardHistoryService clipboardHistoryService;

    @PostMapping("/create")
    @Operation(summary = "创建剪贴板")
    public ResultData<String> createClipboard(@RequestBody CreateClipboardVO createClipboardVO)
    {
        return clipboardHistoryService.createClipboard(createClipboardVO);
    }

    @PostMapping("/getByPage")
    @Operation(summary = "分页获取剪贴板")
    public ResultData<PageResult<ClipboardRespVO>> getClipboardByPage(@RequestBody ClipboardPageVO clipboardPageVO)
    {
        return ResultData.ok(clipboardHistoryService.getClipboard(clipboardPageVO));
    }


}
