package com.whli.autumn.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.whli.autumn.core.web.service.IBaseService;
import com.whli.autumn.model.system.UserRole;
import com.whli.autumn.system.dto.UserDTO;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>用户角色业务</p>
 * @author whli
 * @version 1.0.0
 * @since 2019-09-01 17:13
 */
public interface IUserRoleService extends IBaseService<UserRole>{

    /**
     * 依据角色分页查询授权用户
     * @param entity com.whli.autumn.system.dto.UserDTO
     * @return
     */
    IPage<UserDTO> listUserByRole(UserDTO entity, Integer pageNum, Integer pageSize);

    /**
     * 新增或删除用户与角色关系
     * @param entity com.whli.autumn.system.dto.UserDTO
     * @return
     */
    @Transactional
    int createOrDeleteUserRole(UserDTO entity);
}
