package com.whli.autumn.system.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.whli.autumn.core.web.dao.IBaseDao;
import com.whli.autumn.model.system.User;
import com.whli.autumn.system.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>用户数据操作</p>
 * @author whli
 * @version 1.0.0
 * @since 2019-09-01 17:13
 */
@Mapper
public interface IUserDao extends IBaseDao<User> {

    /**
     * 根据用户名、邮箱、手机号或工号查询
     * @param loginName 用户登录名
     * @return
     */
    User getByLoginNameOrEmailOrPhone(@Param("loginName") String loginName);

    /**
     * 根据第三方登录唯一键查询
     * @param unionId 第三方唯一键
     * @return
     */
    User getByUnionId(@Param("unionId") String unionId);

    /**
     * 依据公司、部门分页查询用户
     * @param page com.baomidou.mybatisplus.core.metadata.IPage
     * @param entity com.whli.autumn.system.dto.UserDTO
     * @return java.util.List<com.whli.autumn.model.system.User>
     */
    List<User> listUserByPage(IPage<User> page, @Param("entity") UserDTO entity);

    /**
     * 启用/禁用用户
     * @param entity com.whli.autumn.system.dto.UserDTO
     * @return
     */
    int updateEnabledByPks(@Param("entity") UserDTO entity);

    /**
     * 验证用户登录名、手机号、邮箱唯一性
     * @param entity com.whli.autumn.model.system.User
     * @return com.whli.autumn.model.system.User
     */
    User validate(@Param("entity") User entity);
}
