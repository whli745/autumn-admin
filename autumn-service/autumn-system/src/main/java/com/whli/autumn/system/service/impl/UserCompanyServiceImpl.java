package com.whli.autumn.system.service.impl;

import com.whli.autumn.core.web.dao.IBaseDao;
import com.whli.autumn.core.web.service.impl.BaseServiceImpl;
import com.whli.autumn.system.dao.IUserCompanyDao;
import com.whli.autumn.model.system.UserCompany;
import com.whli.autumn.system.service.IUserCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>用户公司关系业务实现</p>
 * @author whli
 * @version 1.0.0
 * @since 2019-10-14 20:59
 */
@Service("userCompanyService")
public class UserCompanyServiceImpl extends BaseServiceImpl<UserCompany> implements IUserCompanyService {

	@Autowired
	private IUserCompanyDao userCompanyDao;

	@Override
	public IBaseDao<UserCompany> getDao() {
		return userCompanyDao;
	}

	@Override
	public List<String> listCompanyIdByUserId(String userId) {
		return userCompanyDao.listCompanyIdByUserId(userId);
	}

	@Override
	public List<String> listUserIdByCompanyId(String companyId) {
		return userCompanyDao.listUserIdByCompanyId(companyId);
	}
}
