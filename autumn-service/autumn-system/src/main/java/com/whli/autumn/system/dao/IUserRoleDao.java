package com.whli.autumn.system.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.whli.autumn.core.web.dao.IBaseDao;
import com.whli.autumn.model.system.UserRole;
import com.whli.autumn.system.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>用户角色关系数据操作</p>
 * @author whli
 * @version 1.0.0
 * @since 2019-09-01 17:13
 */
@Mapper
public interface IUserRoleDao extends IBaseDao<UserRole> {

    /**
     * 依据角色查询授权用户
     * @param entity com.whli.autumn.system.dto.UserDTO
     * @return
     */
    List<UserDTO> listUserByRole(IPage<UserDTO> page, @Param("entity") UserDTO entity);
}
