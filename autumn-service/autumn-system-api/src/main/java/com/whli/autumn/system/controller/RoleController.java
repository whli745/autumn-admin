package com.whli.autumn.system.controller;

import com.whli.autumn.core.web.controller.BaseController;
import com.whli.autumn.core.web.service.IBaseService;
import com.whli.autumn.model.system.Role;
import com.whli.autumn.system.service.IRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>角色API</p>
 * @author whli
 * @version 1.0.0
 * @since 2019-09-01 17:13
 */
@RestController
@RequestMapping(value="/system/role")
@Api(description = "角色API")
public class RoleController extends BaseController<Role>{

	@Autowired
	private IRoleService roleService;
	
	@Override
    public IBaseService<Role> getService() {
        return roleService;
    }

    /**
     * 验证角色编码的唯一性
     * @param entity com.whli.autumn.model.system.Role
     * @param request  javax.servlet.http.HttpServletRequest
     * @return java.lang.String
     * @throws Exception
     */
    @PostMapping("validate")
    @ApiOperation("验证角色的唯一性")
    public String validate(Role entity, HttpServletRequest request) throws Exception{
        Role temp = roleService.validate(entity);
        if (temp == null){
            return "true";
        }
        return "false";
    }
}

