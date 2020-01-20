package com.whli.autumn.core.web.service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <p>基本的登录验证</p>
 * @author  whli
 * @version 1.0.0
 * @since  2019/6/23 9:45
 */
public interface IBaseAuthService {

    /**
     * 登录
     * @param request
     * @return
     */
    public Map<String,Object> login(HttpServletRequest request);

    /**
     * 退出登录
     * @return
     */
    public boolean logout(HttpServletRequest request);
}
