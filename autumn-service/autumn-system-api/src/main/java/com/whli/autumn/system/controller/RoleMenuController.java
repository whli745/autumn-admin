package com.whli.autumn.system.controller;

import com.whli.autumn.core.share.ResponseBean;
import com.whli.autumn.core.web.controller.BaseController;
import com.whli.autumn.core.web.service.IBaseService;
import com.whli.autumn.model.system.RoleMenu;
import com.whli.autumn.system.dto.RoleMenuDTO;
import com.whli.autumn.system.service.IRoleMenuService;
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
 * <p>角色菜单关系API</p>
 * @author whli
 * @version 1.0.0
 * @since 2019-09-01 17:13
 */
@RestController
@RequestMapping(value="/system/roleMenu")
@Api(description = "角色菜单关系API")
public class RoleMenuController extends BaseController<RoleMenu>{

	@Autowired
	private IRoleMenuService roleMenuService;
	
	@Override
    public IBaseService<RoleMenu> getService() {
        return roleMenuService;
    }

    /**
     * 根据角色查询菜单
     * @param entity com.whli.autumn.model.system.RoleMenu
     * @param request javax.servlet.http.HttpServletRequest
     * @return
     */
    @PostMapping("listByRole")
    @ApiOperation("根据角色查询菜单")
    public ResponseBean listByRole(@RequestBody RoleMenu entity,HttpServletRequest request) throws Exception{
        List<String> menuIds = roleMenuService.listByRole(entity.getRoleId());
        return ResponseBean.success(menuIds);
    }

    /**
     * 授权菜单
     * @param entity com.whli.autumn.system.dto.RoleMenuDTO
     * @param request javax.servlet.http.HttpServletRequest
     * @return
     * @throws Exception
     */
    @PostMapping("grantMenu")
    @ApiOperation("授权菜单")
    public ResponseBean grantMenu(@RequestBody RoleMenuDTO entity, HttpServletRequest request) throws Exception{
        int rows = roleMenuService.grantMenu(entity);
        if (rows > 0){
           return ResponseBean.success("授权菜单成功");
        }
        return ResponseBean.success("授权菜单失败");
    }
}

