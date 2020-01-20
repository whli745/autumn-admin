package com.whli.autumn.system.dao;

import com.whli.autumn.core.web.dao.IBaseDao;
import com.whli.autumn.model.system.UserCompany;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>用户公司关系数据操作</p>
 * @author whli
 * @version 1.0.0
 * @since 2019-10-14 20:59
 */
@Mapper
public interface IUserCompanyDao extends IBaseDao<UserCompany> {

    /**
     * <p>根据用户ID查询授权公司ID</p>
     * @param userId 用户ID
     * @return
     */
    List<String> listCompanyIdByUserId(@Param("userId") String userId);

    /**
     * <p>根据公司ID查询授权用户ID</p>
     * @param companyId 公司ID
     * @return
     */
    List<String> listUserIdByCompanyId(@Param("companyId") String companyId);
}
