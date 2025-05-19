package top.yangsc.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.yangsc.base.pojo.ClipboardValues;


import java.util.Map;

@Mapper
public interface ClipboardValuesMapper extends BaseMapper<ClipboardValues> {
    Map<String,Object> insertOne(String content);

}
