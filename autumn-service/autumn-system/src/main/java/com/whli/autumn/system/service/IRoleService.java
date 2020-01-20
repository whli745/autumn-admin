package com.whli.autumn.system.service;

import com.whli.autumn.core.web.service.IBaseService;
import com.whli.autumn.model.system.Role;

import java.util.List;

/**
 * <p>角色业务</p>
 * @author whli
 * @version 1.0.0
 * @since 2019-09-01 17:13
 */
public interface IRoleService extends IBaseService<Role>{

    /**
     * 根据用户ID查询角色
     * @param userId java.lang.String 用户ID
     * @return
     */
    List<Role> listByUser(String userId);

    /**
     * 验证角色编码的唯一性
     * @param entity com.whli.autumn.model.system.Role
     * @return
     */
    Role validate(Role entity);
}
