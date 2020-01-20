package com.whli.autumn.system.dao;

import com.whli.autumn.core.web.dao.IBaseDao;
import com.whli.autumn.model.system.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>角色数据操作</p>
 * @author whli
 * @version 1.0.0
 * @since 2019-09-01 17:13
 */
@Mapper
public interface IRoleDao extends IBaseDao<Role> {

    /**
     * 根据用户ID查询角色
     * @param userId java.lang.String 用户ID
     * @return java.util.List
     */
    List<Role> listByUser(String userId);


    /**
     * 验证角色编码唯一性
     * @param entity com.whli.autumn.model.system.Role
     * @return com.whli.autumn.model.system.Role
     */
    Role validate(@Param("entity") Role entity);
}
