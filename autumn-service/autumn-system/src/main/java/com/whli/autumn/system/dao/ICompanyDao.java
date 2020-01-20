package com.whli.autumn.system.dao;

import com.whli.autumn.core.web.dao.IBaseDao;
import com.whli.autumn.model.system.Company;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>公司数据操作</p>
 * @author whli
 * @version 1.0.0
 * @since 2019-09-01 17:13
 */
@Mapper
public interface ICompanyDao extends IBaseDao<Company> {

    /**
     * 依据登录用户查询
     * @param loginName 用户登录名
     * @return java.util.List<com.whli.autumn.model.system.Company>
     */
    List<Company> listCompanyByLoginName(String loginName);

    /**
     * 验证公司名称、排序唯一性
     * @param entity com.whli.autumn.model.system.Company
     * @return com.whli.autumn.model.system.Company
     */
    Company validate(@Param("entity") Company entity);
}
