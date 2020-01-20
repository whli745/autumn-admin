package com.whli.autumn.system.dao;

import com.whli.autumn.core.web.dao.IBaseDao;
import com.whli.autumn.model.system.Depart;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>部门数据操作</p>
 * @author whli
 * @version 1.0.0
 * @since 2019-10-26 00:20
 */
@Mapper
public interface IDepartDao extends IBaseDao<Depart> {


    /**
     * 验证部门编码唯一性
     * @param entity com.whli.autumn.model.system.Depart
     * @return com.whli.autumn.model.system.Depart
     */
    Depart validate(@Param("entity") Depart entity);

}
