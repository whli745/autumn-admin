package com.whli.autumn.system.service;

import com.whli.autumn.core.web.service.IBaseService;
import com.whli.autumn.model.system.RoleMenu;
import com.whli.autumn.system.dto.RoleMenuDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>角色菜单关系业务</p>
 * @author whli
 * @version 1.0.0
 * @since 2019-09-01 17:13
 */
public interface IRoleMenuService extends IBaseService<RoleMenu>{

    /**
     * 根据角色查询菜单
     * @param roleId java.lang.String 角色ID
     * @return
     */
    List<String> listByRole(String roleId);

    /**
     * 授权菜单
     * @param entity com.whli.autumn.system.dto.RoleMenuDTO
     * @return
     */
    @Transactional
    int grantMenu(RoleMenuDTO entity);
}
