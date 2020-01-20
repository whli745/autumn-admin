package com.whli.autumn.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.whli.autumn.core.util.WebUtils;
import com.whli.autumn.core.web.dao.IBaseDao;
import com.whli.autumn.core.web.service.impl.BaseServiceImpl;
import com.whli.autumn.model.system.UserRole;
import com.whli.autumn.system.dao.IUserRoleDao;
import com.whli.autumn.system.dto.UserDTO;
import com.whli.autumn.system.service.IUserRoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>用户角色关系业务实现</p>
 * @author whli
 * @version 1.0.0
 * @since 2019-09-01 17:13
 */
@Service("userRoleService")
public class UserRoleServiceImpl extends BaseServiceImpl<UserRole> implements IUserRoleService {

	@Autowired
	private IUserRoleDao userRoleDao;

	@Override
	public IBaseDao<UserRole> getDao() {
		return userRoleDao;
	}

	@Override
	public IPage<UserDTO> listUserByRole(UserDTO entity, Integer pageNum, Integer pageSize) {
		Page<UserDTO> page = new Page<UserDTO>(pageNum,pageSize);
		page.setRecords(userRoleDao.listUserByRole(page,entity));
		return page;
	}

	@Override
	public int createOrDeleteUserRole(UserDTO entity) {
		int rows = 0;
		if (StringUtils.isNotBlank(entity.getUrId())){
			rows = this.deleteByPK(entity.getUrId());
		}else {
			UserRole userRole = new UserRole();
			userRole.setUserId(entity.getId());
			userRole.setRoleId(entity.getRoleId());
			rows = this.save(userRole);
		}

		return rows;
	}
}
