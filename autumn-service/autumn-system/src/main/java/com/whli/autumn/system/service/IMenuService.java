package com.whli.autumn.system.service;

import com.whli.autumn.core.web.service.IBaseService;
import com.whli.autumn.model.system.Menu;
import com.whli.autumn.system.dto.MenuDTO;

import java.util.List;

/**
 * <p>菜单业务</p>
 * @author whli
 * @version 1.0.0
 * @since 2019-09-01 17:13
 */
public interface IMenuService extends IBaseService<Menu>{

    /**
     * <p>依据登录用户、公司、父ID查询授权菜单</p>
     * @param entity [companyId、userId、parentId]
     * @return
     */
    List<MenuDTO> listMenuByUserAndCompanyAndParent(MenuDTO entity);

    /**
     * <p>依据登录用户、公司、父ID查询授权按钮</p>
     * @param entity [companyId、userId、parentUrl]
     * @return
     */
    List<Menu> listButtonByUserAndCompanyAndParentUrl(MenuDTO entity);

    /**
     * 查询所有菜单(按钮除外)
     * @return
     */
    List<Menu> listAllMenu();

    /**
     * 验证菜单名称、排序唯一性
     * @param entity com.whli.autumn.model.system.Menu
     * @return
     */
    Menu validate(Menu entity);
}
