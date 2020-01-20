package com.whli.autumn.system.dao;

import com.whli.autumn.core.web.dao.IBaseDao;
import com.whli.autumn.model.system.RoleMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>角色菜单关系数据操作</p>
 * @author whli
 * @version 1.0.0
 * @since 2019-09-01 17:13
 */
@Mapper
public interface IRoleMenuDao extends IBaseDao<RoleMenu> {

    /**
     * 根据角色删除
     * @param roleId java.lang.String 角色ID
     * @return java.lang.int
     */
    int deleteByRole(@Param("roleId") String roleId);

    /**
     * 根据角色查询菜单
     * @param roleId java.lang.String 角色ID
     * @return java.util.List<java.lang.String>
     */
    List<String> listByRole(@Param("roleId") String roleId);
}
