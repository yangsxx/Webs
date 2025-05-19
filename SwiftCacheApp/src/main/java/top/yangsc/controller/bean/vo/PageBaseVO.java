package top.yangsc.controller.bean.vo;

import lombok.Data;
import top.yangsc.base.Exception.ParameterValidationException;
import top.yangsc.parameterValidation.BaseVO;


@Data
public class PageBaseVO implements BaseVO {
    public long pageNum;
    public long pageSize;
    private long offset;

    @Override
    public void validation() {
        if (pageNum <= 0) {
            throw new ParameterValidationException("pageNum必须大于0");
        }
        if (pageSize <= 0) {
            throw new ParameterValidationException("pageSize必须大于0");
        }
    }
}
