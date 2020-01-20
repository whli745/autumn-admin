package com.whli.autumn.system.service.impl;

import com.whli.autumn.core.exception.BusinessException;
import com.whli.autumn.core.web.dao.IBaseDao;
import com.whli.autumn.core.web.service.impl.BaseServiceImpl;
import com.whli.autumn.model.system.RoleMenu;
import com.whli.autumn.system.dao.IRoleMenuDao;
import com.whli.autumn.system.dto.RoleMenuDTO;
import com.whli.autumn.system.service.IRoleMenuService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>角色菜单关系业务实现</p>
 * @author whli
 * @version 1.0.0
 * @since 2019-09-01 17:13
 */
@Service("roleMenuService")
public class RoleMenuServiceImpl extends BaseServiceImpl<RoleMenu> implements IRoleMenuService {

	@Autowired
	private IRoleMenuDao roleMenuDao;

	@Override
	public IBaseDao<RoleMenu> getDao() {
		return roleMenuDao;
	}

	@Override
	public List<String> listByRole(String roleId) {
		return roleMenuDao.listByRole(roleId);
	}

	@Override
	public int grantMenu(RoleMenuDTO entity) {

		if (StringUtils.isBlank(entity.getRoleId()) || CollectionUtils.isEmpty(entity.getMenuIds())){
			throw new BusinessException("请选择授权角色或菜单");
		}

		roleMenuDao.deleteByRole(entity.getRoleId());

		entity.getMenuIds().forEach(menuId -> {
			RoleMenu temp = new RoleMenu();
			temp.setRoleId(entity.getRoleId());
			temp.setMenuId(menuId);
			this.save(temp);
		});

		return 1;
	}
}
