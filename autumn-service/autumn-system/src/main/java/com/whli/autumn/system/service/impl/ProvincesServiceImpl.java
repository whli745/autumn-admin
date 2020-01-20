package com.whli.autumn.system.service.impl;

import com.whli.autumn.core.web.dao.IBaseDao;
import com.whli.autumn.core.web.service.impl.BaseServiceImpl;
import com.whli.autumn.system.dao.IProvincesDao;
import com.whli.autumn.model.system.Provinces;
import com.whli.autumn.system.service.IProvincesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>区域业务实现</p>
 * @author whli
 * @version 1.0.0
 * @since 2019-09-01 17:13
 */
@Service("provincesService")
public class ProvincesServiceImpl extends BaseServiceImpl<Provinces> implements IProvincesService {

	@Autowired
	private IProvincesDao provincesDao;

	@Override
	public IBaseDao<Provinces> getDao() {
		return provincesDao;
	}
}
