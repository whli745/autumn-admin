
package com.whli.autumn.system.controller;

import com.whli.autumn.core.share.ResponseBean;
import com.whli.autumn.core.web.controller.BaseController;
import com.whli.autumn.core.web.service.IBaseService;
import com.whli.autumn.model.system.Menu;
import com.whli.autumn.system.dto.MenuDTO;
import com.whli.autumn.system.service.IMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>菜单API</p>
 * @author whli
 * @version 1.0.0
 * @since 2019-09-01 17:13
 */
@RestController
@RequestMapping(value="/system/menu")
@Api(description = "菜单API")
public class MenuController extends BaseController<Menu>{

	@Autowired
	private IMenuService menuService;
	
	@Override
    public IBaseService<Menu> getService() {
        return menuService;
    }

    /**
     * 依据登录用户及公司查询用户授权菜单
     * @param entity com.whli.autumn.system.dto.MenuDTO
     * @param request javax.servlet.http.HttpServletRequest
     * @return
     * @throws Exception
     */
    @PostMapping("listMenuByUserAndCompanyAndParent")
    @ApiOperation("依据登录用户及公司查询用户授权菜单")
    public ResponseBean listMenuByUserAndCompanyAndParent(@RequestBody MenuDTO entity, HttpServletRequest request) throws Exception{
        List<MenuDTO> menus = menuService.listMenuByUserAndCompanyAndParent(entity);
        return ResponseBean.success(menus);
	}

    /**
     * 依据登录用户及公司查询用户授权菜单
     * @param entity com.whli.autumn.system.dto.MenuDTO
     * @param request javax.servlet.http.HttpServletRequest
     * @return
     * @throws Exception
     */
    @PostMapping("listButtonByUserAndCompanyAndParentUrl")
    @ApiOperation("依据登录用户及公司查询用户授权按钮")
    public ResponseBean listButtonByUserAndCompanyAndParentUrl(@RequestBody MenuDTO entity, HttpServletRequest request) throws Exception{
        List<Menu> menus = menuService.listButtonByUserAndCompanyAndParentUrl(entity);
        return ResponseBean.success(menus);
    }

    /**
     * 查询所有菜单(按钮除外)
     * @param request javax.servlet.http.HttpServletRequest
     * @return
     * @throws Exception
     */
    @PostMapping("listAllMenu")
    @ApiOperation("查询所有菜单")
    public ResponseBean listAllMenu(HttpServletRequest request) throws Exception{
        List<Menu> menus = menuService.listAllMenu();
        return ResponseBean.success(menus);
    }

    /**
     * 验证菜单名称、排序的唯一性
     * @param entity  com.whli.autumn.model.system.Menu
     * @param request  javax.servlet.http.HttpServletRequest
     * @return java.lang.String
     * @throws Exception
     */
    @PostMapping("validate")
    @ApiOperation("验证菜单名称、排序的唯一性")
    public String validate(Menu entity, HttpServletRequest request) throws Exception{
        Menu temp = menuService.validate(entity);
        if (temp == null){
            return "true";
        }
        return "false";
    }
}


