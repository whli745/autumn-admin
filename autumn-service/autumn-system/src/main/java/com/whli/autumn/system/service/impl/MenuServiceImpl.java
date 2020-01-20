package com.whli.autumn.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.whli.autumn.core.web.dao.IBaseDao;
import com.whli.autumn.core.web.service.impl.BaseServiceImpl;
import com.whli.autumn.model.system.Menu;
import com.whli.autumn.model.system.User;
import com.whli.autumn.system.dao.IMenuDao;
import com.whli.autumn.system.dao.IUserDao;
import com.whli.autumn.system.dto.MenuDTO;
import com.whli.autumn.system.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>菜单业务实现</p>
 * @author whli
 * @version 1.0.0
 * @since 2019-09-01 17:13
 */
@Service("menuService")
public class MenuServiceImpl extends BaseServiceImpl<Menu> implements IMenuService {

	@Autowired
	private IMenuDao menuDao;
	@Autowired
	private IUserDao userDao;

	@Override
	public IBaseDao<Menu> getDao() {
		return menuDao;
	}

	@Override
	public List<Menu> listAll(Menu entity) {
		QueryWrapper<Menu> wrapper = Wrappers.query(entity);
		wrapper.orderByAsc("sort");
		return menuDao.selectList(wrapper);
	}

	@Override
	public List<MenuDTO> listMenuByUserAndCompanyAndParent(MenuDTO entity) {

		User user = userDao.selectById(entity.getUserId());
		if ("1".equals(user.getSuperAdmin())){
			entity.setSuperAdmin("1");
		}

		return menuDao.listMenuByUserAndCompanyAndParent(entity);
	}

	@Override
	public List<Menu> listButtonByUserAndCompanyAndParentUrl(MenuDTO entity) {
		User user = userDao.selectById(entity.getUserId());
		if ("1".equals(user.getSuperAdmin())){
			entity.setSuperAdmin("1");
		}
		return menuDao.listButtonByUserAndCompanyAndParentUrl(entity);
	}

	@Override
	public List<Menu> listAllMenu() {
		QueryWrapper<Menu> queryWrapper = Wrappers.query();
		queryWrapper.eq(true,"enabled","1");
		queryWrapper.and(wrapper -> {
			wrapper.isNull("target").or().ne("target","BUTTON");
		});
		return menuDao.selectList(queryWrapper);
	}

	@Override
	public Menu validate(Menu entity) {
		return menuDao.validate(entity);
	}

	@Override
	public int save(Menu entity) {
		int rows = super.save(entity);
		if ("TAB".equals(entity.getTarget())){
			List<Menu> buttons = new ArrayList<Menu>();
			//新增按钮
			Menu addBtn = new Menu();
			addBtn.setParentId(entity.getId());
			addBtn.setEnabled("1");
			addBtn.setHref("btn_add");
			addBtn.setSort(1);
			addBtn.setName("新增");
			addBtn.setTarget("BUTTON");
			buttons.add(addBtn);
			//修改按钮
			Menu updateBtn = new Menu();
			updateBtn.setParentId(entity.getId());
			updateBtn.setEnabled("1");
			updateBtn.setHref("btn_edit");
			updateBtn.setSort(2);
			updateBtn.setName("编辑");
			updateBtn.setTarget("BUTTON");
			buttons.add(updateBtn);
			//删除按钮
			Menu delBtn = new Menu();
			delBtn.setParentId(entity.getId());
			delBtn.setEnabled("1");
			delBtn.setHref("btn_delete");
			delBtn.setSort(3);
			delBtn.setName("删除");
			delBtn.setTarget("BUTTON");
			buttons.add(delBtn);
			rows += super.saveBatch(buttons);
		}
		return rows;
	}
}
