package top.yangsc.services;

import com.baomidou.mybatisplus.extension.service.IService;
import top.yangsc.base.pojo.KeyTable;
import top.yangsc.config.PageResult;
import top.yangsc.controller.bean.vo.KeyTablePageVO;
import top.yangsc.controller.bean.vo.UpdateKeyValueVO;
import top.yangsc.controller.bean.vo.resp.KeyTableRespVO;


public interface KeyTableService extends IService<KeyTable> {
    boolean createKeyTable(UpdateKeyValueVO createKeyTableVO);

    boolean updateKeyTable(UpdateKeyValueVO createKeyTableVO);

    KeyTableRespVO getValue(Long id);

    boolean delete(Long id);

    boolean updatePermission(Long id, int permission);

    PageResult<KeyTableRespVO> getKeyTableByPage(KeyTablePageVO pageBaseVO);
}
