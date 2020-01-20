package com.whli.autumn.system.dao;

import com.whli.autumn.core.web.dao.IBaseDao;
import com.whli.autumn.model.system.Dict;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>字典数据操作</p>
 * @author whli
 * @version 1.0.0
 * @since 2019-09-01 17:13
 */
@Mapper
public interface IDictDao extends IBaseDao<Dict> {

    /**
     * 依据Code查询字典
     * @param code java.lang.String 字典编码
     * @return
     */
    List<Dict> listByCode(String code);

    /**
     * 验证字典编码、排序唯一性
     * @param entity com.whli.autumn.model.system.Dict
     * @return com.whli.autumn.model.system.Barcode
     */
    Dict validate(@Param("entity") Dict entity);
}
