package top.yangsc.controller.bean.vo;


import lombok.Data;
import top.yangsc.parameterValidation.annotation.NotNull;


@Data
public class CreateClipboardVO {
    @NotNull
    private String content;

    @NotNull
    private String systemTag;

    @NotNull
    private String tagName;


}
