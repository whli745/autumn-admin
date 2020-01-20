package com.whli.autumn.system.service.impl;

import com.whli.autumn.core.web.dao.IBaseDao;
import com.whli.autumn.core.web.service.impl.BaseServiceImpl;
import com.whli.autumn.system.dao.IRoleDao;
import com.whli.autumn.model.system.Role;
import com.whli.autumn.system.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>角色业务实现</p>
 * @author whli
 * @version 1.0.0
 * @since 2019-09-01 17:13
 */
@Service("roleService")
public class RoleServiceImpl extends BaseServiceImpl<Role> implements IRoleService {

	@Autowired
	private IRoleDao roleDao;

	@Override
	public IBaseDao<Role> getDao() {
		return roleDao;
	}

	@Override
	public List<Role> listByUser(String userId) {
		return roleDao.listByUser(userId);
	}

	@Override
	public Role validate(Role entity) {
		return roleDao.validate(entity);
	}
}
