package top.yangsc.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.yangsc.base.pojo.KeyTable;
import top.yangsc.controller.bean.vo.KeyTablePageVO;
import top.yangsc.controller.bean.vo.PageBaseVO;


import java.util.List;

@Mapper
public interface KeyTableMapper extends BaseMapper<KeyTable> {
    int findMaxVersion(Long id);

    List<KeyTable> selectByPage(PageBaseVO pageBaseVO);

    Long selectCountByPage(KeyTablePageVO pageBaseVO);
}
