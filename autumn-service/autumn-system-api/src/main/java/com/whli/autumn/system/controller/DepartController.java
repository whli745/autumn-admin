package com.whli.autumn.system.controller;

import com.whli.autumn.core.web.controller.BaseController;
import com.whli.autumn.core.web.service.IBaseService;
import com.whli.autumn.model.system.Depart;
import com.whli.autumn.system.service.IDepartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>部门API</p>
 * @author whli
 * @version 1.0.0
 * @since 2019-10-26 00:20
 */
@RestController
@RequestMapping(value="/system/depart")
@Api(description = "部门API")
public class DepartController extends BaseController<Depart>{

	@Autowired
	private IDepartService departService;
	
	@Override
    public IBaseService<Depart> getService() {
        return departService;
    }

    /**
     * 验证部门编码的唯一性
     * @param entity com.whli.autumn.model.system.Depart
     * @param request  javax.servlet.http.HttpServletRequest
     * @return java.lang.String
     * @throws Exception
     */
    @PostMapping("validate")
    @ApiOperation("验证部门编码的唯一性")
    public String validate(Depart entity, HttpServletRequest request) throws Exception{
        Depart temp = departService.validate(entity);
        if (temp == null){
            return "true";
        }
        return "false";
    }
}

