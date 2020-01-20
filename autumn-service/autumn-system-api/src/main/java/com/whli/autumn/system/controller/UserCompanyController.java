package com.whli.autumn.system.controller;

import com.whli.autumn.core.web.controller.BaseController;
import com.whli.autumn.core.web.service.IBaseService;
import com.whli.autumn.model.system.UserCompany;
import com.whli.autumn.system.service.IUserCompanyService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>用户公司关系API</p>
 * @author whli
 * @version 1.0.0
 * @since 2019-10-14 20:59
 */
@RestController
@RequestMapping(value="/system/userCompany")
@Api(description = "用户公司关系API")
public class UserCompanyController extends BaseController<UserCompany>{

	@Autowired
	private IUserCompanyService userCompanyService;
	
	@Override
    public IBaseService<UserCompany> getService() {
        return userCompanyService;
    }
}

