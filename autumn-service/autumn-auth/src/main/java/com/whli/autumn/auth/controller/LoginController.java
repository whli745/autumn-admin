package com.whli.autumn.auth.controller;

import com.whli.autumn.core.share.ResponseBean;
import com.whli.autumn.core.web.service.IBaseAuthService;
import com.whli.autumn.model.system.Company;
import com.whli.autumn.system.service.ICompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * <p>类描述</p>
 * @author whli
 * @version 1.0.0
 * @since 2019/9/28 13:46
 */
@RestController
@RequestMapping(path = "/auth")
public class LoginController {

    @Autowired
    private IBaseAuthService authService;
    @Autowired
    private ICompanyService companyService;

    @PostMapping("/login")
    public ResponseBean login(HttpServletRequest request) throws Exception{
        Map<String, Object> result = authService.login(request);
        return ResponseBean.success(null,result);
    }

    @PostMapping("/logout")
    public ResponseBean logout(HttpServletRequest request) throws Exception{
        authService.logout(request);
        return ResponseBean.success("");
    }

    @PostMapping("/listAllCompany")
    public ResponseBean listAllCompany(@RequestBody Company entity, HttpServletRequest request, HttpServletResponse response) throws Exception{
        List<Company> companies = companyService.listAll(entity);
        return ResponseBean.success(null,companies);
    }
}
