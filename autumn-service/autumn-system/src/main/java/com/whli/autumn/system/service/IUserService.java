package com.whli.autumn.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.whli.autumn.core.web.service.IBaseService;
import com.whli.autumn.model.system.User;
import com.whli.autumn.system.dto.UserDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>用户业务</p>
 * @author whli
 * @version 1.0.0
 * @since 2019-09-01 17:13
 */
public interface IUserService extends IBaseService<User>{

    /**
     * 根据用户名、邮箱、手机或工号查询
     * @param loginName
     * @return
     */
    User getByLoginNameOrEmailOrPhone(String loginName);

    /**
     * 根据第三方登录唯一键查询
     * @param unionId
     * @return
     */
    User getByUnionId(String unionId);

    /**
     * 依据公司、部门分页查询用户
     * @param pageNum
     * @param pageSize
     * @param entity
     * @return
     */
    IPage<User> listUserByPage(UserDTO entity, Integer pageNum, Integer pageSize);

    /**
     * 新增用户并关联公司、部门
     * @param entity
     * @return
     */
    @Transactional
    int saveUser(UserDTO entity);

    /**
     * 启用/禁用用户
     * @param entity
     * @return
     */
    @Transactional
    int updateEnabledByPks(UserDTO entity);

    /**
     * 重置密码
     * @param ids
     * @return
     */
    @Transactional
    int resetPassword(List<String> ids);

    /**
     * 更新密码
     * @param entity
     * @return
     */
    @Transactional
    int updatePassword(User entity);

    /**
     *
     * @param entity com.whli.autumn.model.system.User
     * @return
     */
    User validate(User entity);
}
