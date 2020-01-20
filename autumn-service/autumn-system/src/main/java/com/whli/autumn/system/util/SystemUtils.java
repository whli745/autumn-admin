package com.whli.autumn.system.util;

import com.whli.autumn.model.system.Company;
import com.whli.autumn.system.service.ICompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>系统工具类</p>
 * @author whli
 * @version 1.0.0
 * @since 2019/10/14 21:48
 */
@Component
public class SystemUtils {

    private static ICompanyService companyService;

    @Autowired
    public void setCompanyService(ICompanyService companyService) {
        SystemUtils.companyService = companyService;
    }

    public static Company getRootCompany(){
        Company temp = new Company();
        temp.setParentId("0");
        return companyService.getOne(temp);
    }
}
