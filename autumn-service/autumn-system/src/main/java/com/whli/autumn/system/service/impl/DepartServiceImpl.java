package com.whli.autumn.system.service.impl;

import com.whli.autumn.core.web.dao.IBaseDao;
import com.whli.autumn.core.web.service.impl.BaseServiceImpl;
import com.whli.autumn.system.dao.IDepartDao;
import com.whli.autumn.model.system.Depart;
import com.whli.autumn.system.service.IDepartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>部门业务实现</p>
 * @author whli
 * @version 1.0.0
 * @since 2019-10-26 00:20
 */
@Service("departService")
public class DepartServiceImpl extends BaseServiceImpl<Depart> implements IDepartService {

	@Autowired
	private IDepartDao departDao;

	@Override
	public IBaseDao<Depart> getDao() {
		return departDao;
	}

	@Override
	public Depart validate(Depart entity) {
		return departDao.validate(entity);
	}
}
