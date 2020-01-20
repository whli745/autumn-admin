package com.whli.autumn.system.service.impl;

import com.whli.autumn.core.web.dao.IBaseDao;
import com.whli.autumn.core.web.service.impl.BaseServiceImpl;
import com.whli.autumn.model.system.Company;
import com.whli.autumn.system.dao.ICompanyDao;
import com.whli.autumn.system.service.ICompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>公司业务实现</p>
 * @author whli
 * @version 1.0.0
 * @since 2019-09-01 17:13
 */
@Service("companyService")
public class CompanyServiceImpl extends BaseServiceImpl<Company> implements ICompanyService {

	@Autowired
	private ICompanyDao companyDao;

	@Override
	public IBaseDao<Company> getDao() {
		return companyDao;
	}

	@Override
	public List<Company> listCompanyByLoginName(String loginName) {
		return companyDao.listCompanyByLoginName(loginName);
	}

	@Override
	public Company validate(Company entity) {
		return companyDao.validate(entity);
	}
}
