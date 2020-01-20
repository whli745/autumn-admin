package com.whli.autumn.system.controller;

import com.whli.autumn.core.web.controller.BaseController;
import com.whli.autumn.core.web.service.IBaseService;
import com.whli.autumn.model.system.Company;
import com.whli.autumn.system.service.ICompanyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>公司API</p>
 * @author whli
 * @version 1.0.0
 * @since 2019-09-01 17:13
 */
@RestController
@RequestMapping(value="/system/company")
@Api(description = "公司API")
public class CompanyController extends BaseController<Company>{

	@Autowired
	private ICompanyService companyService;
	
	@Override
    public IBaseService<Company> getService() {
        return companyService;
    }

    /**
     * 验证公司名称、排序的唯一性
     * @param entity  公司对象参数
     * @param request  javax.servlet.http.HttpServletRequest
     * @return java.lang.String
     * @throws Exception
     */
    @PostMapping("validate")
    @ApiOperation("验证公司名称、排序的唯一性")
    public String validate(Company entity, HttpServletRequest request) throws Exception{
        Company temp = companyService.validate(entity);
	    if (temp == null){
	        return "true";
        }
	    return "false";
    }
}

