package com.whli.autumn.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.whli.autumn.core.share.ResponseBean;
import com.whli.autumn.core.util.WebUtils;
import com.whli.autumn.core.web.controller.BaseController;
import com.whli.autumn.core.web.service.IBaseService;
import com.whli.autumn.model.system.UserRole;
import com.whli.autumn.system.dto.UserDTO;
import com.whli.autumn.system.service.IUserRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>用户角色关系API</p>
 * @author whli
 * @version 1.0.0
 * @since 2019-09-01 17:13
 */
@RestController
@RequestMapping(value="/system/userRole")
@Api(description = "用户角色关系API")
public class UserRoleController extends BaseController<UserRole>{

	@Autowired
	private IUserRoleService userRoleService;
	
	@Override
    public IBaseService<UserRole> getService() {
        return userRoleService;
    }


    /**
     * 依据角色分页查询授权用户
     * @param entity com.whli.autumn.system.dto.UserDTO
     * @return
     */
    @PostMapping("/listUserByRole")
    @ApiOperation("依据角色分页查询授权用户")
    public ResponseBean listUserByRole(@RequestBody UserDTO entity, HttpServletRequest request) throws Exception{
        entity.setCompanyId(WebUtils.getCurrentCompanyId());
        IPage<UserDTO> page = userRoleService.listUserByRole(entity, entity.getPageNumber(), entity.getPageSize());
        return ResponseBean.getInstance(0,null,page.getRecords(),Long.valueOf(page.getTotal()).intValue());
    }

    /**
     * 新增或删除用户与角色关系
     * @param entity com.whli.autumn.system.dto.UserDTO
     * @param request
     * @return
     * @throws Exception
     */
    @PostMapping("/createOrDeleteUserRole")
    @ApiOperation("新增或删除用户与角色关系")
    public ResponseBean createOrDeleteUserRole(@RequestBody UserDTO entity, HttpServletRequest request) throws Exception{
        int rows = userRoleService.createOrDeleteUserRole(entity);
        if (rows > 0){
            return ResponseBean.success();
        }
        return ResponseBean.fail();
    }
}

