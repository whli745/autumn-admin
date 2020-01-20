package com.whli.autumn.system.service;

import com.whli.autumn.core.web.service.IBaseService;
import com.whli.autumn.model.system.UserCompany;

import java.util.List;

/**
 * <p>用户公司关系业务</p>
 * @author whli
 * @version 1.0.0
 * @since 2019-10-14 20:59
 */
public interface IUserCompanyService extends IBaseService<UserCompany>{

    /**
     * <p>根据用户ID查询授权公司ID</p>
     * @param userId java.lang.String 用户ID
     * @return
     */
    List<String> listCompanyIdByUserId(String userId);

    /**
     * <p>根据公司ID查询授权用户ID</p>
     * @param companyId java.lang.String 公司ID
     * @return
     */
    List<String> listUserIdByCompanyId(String companyId);
}
