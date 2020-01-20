package com.whli.autumn.system.service;

import com.whli.autumn.core.web.service.IBaseService;
import com.whli.autumn.model.system.Company;

import java.util.List;

/**
 * <p>公司业务</p>
 * @author whli
 * @version 1.0.0
 * @since 2019-09-01 17:13
 */
public interface ICompanyService extends IBaseService<Company>{

    /**
     * 依据登录用户名查询
     * @param loginName java.lang.String 用户登录名
     * @return java.util.List<Company>
     */
    List<Company> listCompanyByLoginName(String loginName);

    /**
     * 验证公司名称、排序唯一性
     * @param entity com.whli.autumn.model.system.Company
     * @return
     */
    Company validate(Company entity);
}
