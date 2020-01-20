package com.whli.autumn.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.whli.autumn.core.share.ResponseBean;
import com.whli.autumn.core.util.WebUtils;
import com.whli.autumn.core.web.controller.BaseController;
import com.whli.autumn.core.web.service.IBaseService;
import com.whli.autumn.model.system.User;
import com.whli.autumn.system.dto.UserDTO;
import com.whli.autumn.system.service.IUserService;
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
 * <p>用户API</p>
 * @author whli
 * @version 1.0.0
 * @since 2019-09-01 17:13
 */
@RestController
@RequestMapping(value="/system/user")
@Api(description = "用户API")
public class UserController extends BaseController<User>{

	@Autowired
	private IUserService userService;
	
	@Override
    public IBaseService<User> getService() {
        return userService;
    }

    /**
     * 依据公司、部门分页查询用户
     * @param entity com.whli.autumn.system.dto.UserDTO
     * @return
     */
    @PostMapping("listUserByPage")
    @ApiOperation("依据公司、部门分页查询用户")
    public ResponseBean listUserByPage(@RequestBody UserDTO entity, HttpServletRequest request) throws Exception{
        entity.setCompanyId(WebUtils.getCurrentCompanyId());
        IPage<User> page = userService.listUserByPage(entity, entity.getPageNumber(), entity.getPageSize());
        return ResponseBean.getInstance(0,null,page.getRecords(),new Long(page.getTotal()).intValue());
    }

    /**
     * 新增用户关联系公司
     * @param entity com.whli.autumn.system.dto.UserDTO
     * @param request javax.servlet.http.HttpServletRequest
     * @return
     */
    @PostMapping("saveUser")
    @ApiOperation("新增用户关联系公司")
    public ResponseBean saveUser(@RequestBody UserDTO entity, HttpServletRequest request) throws Exception{
        entity.setCompanyId(WebUtils.getCurrentCompanyId());
        int rows = userService.saveUser(entity);
        if (rows > 0){
            return ResponseBean.success("新增用户成功");
        }
        return ResponseBean.fail("新增用户失败");
    }

    /**
     * 启用、禁用用户
     * @param entity com.whli.autumn.system.dto.UserDTO
     * @param request javax.servlet.http.HttpServletRequest
     * @return
     * @throws Exception
     */
    @PostMapping("updateEnabledByPks")
    @ApiOperation("启用、禁用用户")
    public ResponseBean updateEnabledByPks(@RequestBody UserDTO entity,HttpServletRequest request) throws Exception{
        int rows = userService.updateEnabledByPks(entity);
        if (rows > 0){
            return ResponseBean.success();
        }
        return ResponseBean.fail();
    }

    /**
     * 重置密码
     * @param ids java.util.List<java.lang.String>
     * @param request javax.servlet.http.HttpServletRequest
     * @return
     * @throws Exception
     */
    @PostMapping("resetPassword")
    @ApiOperation("重置密码")
    public ResponseBean resetPassword(@RequestBody List<String>ids, HttpServletRequest request) throws Exception{
        int rows = userService.resetPassword(ids);
        if (rows > 0){
            return ResponseBean.success("重置密码成功");
        }
        return ResponseBean.fail("重置密码失败");
    }

    /**
     * 更新密码
     * @param entity com.whli.autumn.mode.system.User
     * @param request javax.servlet.http.HttpServletRequest
     * @return
     * @throws Exception
     */
    @PostMapping("updatePassword")
    @ApiOperation("修改密码")
    public ResponseBean updatePassword(@RequestBody User entity,HttpServletRequest request) throws Exception{
        int rows = userService.updatePassword(entity);
        if (rows > 0){
            return ResponseBean.success("更新密码成功");
        }
        return ResponseBean.fail("更新密码失败");
    }

    /**
     * 根据第三方登录唯一键查询
     * @param entity com.whli.autumn.model.system.User
     * @param request javax.servlet.http.HttpServletRequest
     * @return
     * @throws Exception
     */
    @PostMapping("getByUnionId")
    @ApiOperation("根据第三方登录唯一键查询")
    public ResponseBean getByUnionId(@RequestBody User entity,HttpServletRequest request) throws Exception{
        User user = userService.getByUnionId(entity.getUnionId());
        return ResponseBean.success(user);
    }

    /**
     * 验证用户登录名、手机、邮箱的唯一性
     * @param entity com.whli.autumn.model.system.User
     * @param request javax.servlet.http.HttpServletRequest
     * @return java.lang.String
     * @throws Exception
     */
    @PostMapping("validate")
    @ApiOperation("验证用户登录名、手机、邮箱的唯一性")
    public String validate(User entity, HttpServletRequest request) throws Exception{
        User temp = userService.validate(entity);
        if (temp == null){
            return "true";
        }
        return "false";
    }
}

