package top.yangsc.controller.bean.vo;

import lombok.Data;
import top.yangsc.base.Exception.ParameterValidationException;
import top.yangsc.controller.bean.vo.resp.ForKeyValue;
import top.yangsc.parameterValidation.BaseVO;
import top.yangsc.parameterValidation.annotation.NotNull;

import java.util.List;

/**
 * 描述：top.yangsc.swiftcache.controller.bean.vo
 *
 * @author yang
 * @date 2025/5/13 15:50
 */
@Data
public class UpdateKeyValueVO implements BaseVO {
    private Long id;
    @NotNull
    private String key;
    private List<ForKeyValue> values;
    private int permission = 0;
    private Long userId;


    @Override
    public void validation() {
        if (values == null || values.isEmpty()) {
            throw new ParameterValidationException("值不可为空！");
        }
    }
}
