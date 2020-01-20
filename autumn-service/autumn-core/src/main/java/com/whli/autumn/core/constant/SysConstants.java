package com.whli.autumn.core.constant;

/**
 * @desc 系统工具类
 * @author  whli
 * @Version 1.0
 * @since  2018/5/29 16:24
 */
public class SysConstants {

    /**
     * 用户默认密码
     */
    public static final String DEFAULT_PASSWORD = "123456";

    /**
     * 用户当前目录
     */
    public static final String USER_HOME = System.getProperty("user.home")+"/upload/";

    /**
     * 存放验证密钥token的header
     */
    public static final String AUTHORIZATION = "Authorization";

    /**
     * 登录用户
     */
    public static final String LOGIN_USER = "loginUser";
    public static final String LOGIN_USER_NAME = "loginName";
    public static final String LOGIN_USER_ID = "loginId";
    public static final String LOGIN_COMPANY_ID = "loginCompanyId";
    public static final String LOGIN_TOKEN = "token";
    public static final String MY_SALT = "secret";

    /**
     * Token过期
     */
    public static final Integer TOKEN_FAIL_CODE = -10002;
    public static final String TOKEN_FAIL_MESSAGE = "token过期,请重新登陆";

    /**
     * 限制帐号登录
     */
    public static final String KICK_OUT = "kickOut";
    public static final Integer KICK_OUT_CODE = -10003;
    public static final String KICK_OUT_MESSAGE = "帐号已在另一处登录，请重新登录";

    /**
     * License过期
     */
    public static final Integer LICENSE_FAIL_CODE = -10004;
    public static final String LICENSE_FAIL_MESSAGE = "未取得授权或证书已过期，请重新申请证书";
}
