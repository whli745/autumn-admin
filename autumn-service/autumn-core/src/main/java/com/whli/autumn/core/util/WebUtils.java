package com.whli.autumn.core.util;

import com.whli.autumn.core.constant.SysConstants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 使用于web工程的工具类
 *
 * @author whli
 * @since 2018/5/6 16:35
 */
public class WebUtils {

    private WebUtils() {
    }

    /**
     * 获取Token
     *
     * @return
     */
    public static String getToken() {
        String token = getRequest().getHeader(SysConstants.AUTHORIZATION);
        if (StringUtils.isNotBlank(token)){
            return token;
        }
        return "";
    }

    /**
     * 查询是否存在token
     *
     * @param token
     * @return
     */
    public static boolean existsToken(String token) {
        return RedisUtils.exists(token);
    }

    /**
     * 获取登录用户名
     *
     * @return string
     */
    public static String getCurrentUserName() {

        String token = getToken();
        if (StringUtils.isNotBlank(token) && existsToken(token)){
            String subject = (String) RedisUtils.hGet(token,SysConstants.LOGIN_USER_NAME);
            return subject;
        }
        return "";
    }


    /**
     * 获取登录用户id
     *
     * @return string
     */
    public static String getCurrentUserId() {
        String token = getToken();
        if (StringUtils.isNotBlank(token) && existsToken(token)){
            String loginId = (String) RedisUtils.hGet(token,SysConstants.LOGIN_USER_ID);
            return loginId;
        }
        return null;
    }

    /**
     * 获取登录用户id
     *
     * @return string
     */
    public static String getCurrentCompanyId() {
        String token = getToken();
        if (StringUtils.isNotBlank(token) && existsToken(token)){
            String companyId = (String) RedisUtils.hGet(token,SysConstants.LOGIN_COMPANY_ID);
            return companyId;
        }
        return null;
    }


    /**
     * 获取完整请求url
     *
     * @param request
     * @return string
     */
    public static String getRequestURI(HttpServletRequest request) {
        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getRequestURI();
    }

    /**
     * 获取远程用户访问的Ip
     *
     * @param request
     * @return
     */
    public static String getRemoteIP(HttpServletRequest request) {
        String ipAddress = request.getHeader("x-forwarded-for");
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if ("127.0.0.1".equals(ipAddress) || "0:0:0:0:0:0:0:1".equals(ipAddress)) {
                //根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ipAddress = inet.getHostAddress();
            }
        }
        //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ipAddress != null && ipAddress.length() > 15) { //"***.***.***.***".length() = 15
            if (ipAddress.indexOf(",") > 0) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
            }
        }
        return ipAddress;
    }

    /**
     * @desc 将Json字符串返回前端
     * @author whli
     * @Version 1.0
     * @Params [response, json]
     * @Return void
     * @since 2018/6/3 10:07
     */
    public static void returnJson(HttpServletResponse response, String json) {
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        try {
            writer = response.getWriter();
            writer.print(json);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    /**
     * @desc 由Spring容器获取HttpServletRequest
     * @author whli
     * @Version 1.0
     * @Return HttpServletRequest
     * @since 2018/6/3 10:07
     */
    public static HttpServletRequest getRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            return null;
        }

        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        try {
            if (null != request) {
                request.setCharacterEncoding("UTF-8");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return request;
    }

    /**
     * @desc 由Spring容器获取HttpServletResponse
     * @author whli
     * @Version 1.0
     * @Params [response, json]
     * @Return HttpServletResponse
     * @since 2018/6/3 10:07
     */
    public static HttpServletResponse getResponse() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            return null;
        }

        HttpServletResponse response = ((ServletRequestAttributes) requestAttributes).getResponse();

        if (null != response) {
            response.setCharacterEncoding("UTF-8");
        }

        return response;
    }

    /**
     * 判断是否Ajax请求
     *
     * @param request
     * @return
     */
    public static boolean isAjax(HttpServletRequest request) {
        return !StringUtils.isNotBlank(request.getHeader("x-requested-with"))
                && "XMLHttpRequest".equals(request.getHeader("x-requested-with"));
    }

    /**
     * 判断是否移动端
     * @param request
     * @return
     */
    public static boolean isMobile(HttpServletRequest request){
        String info= request.getHeader("user-agent");
        if (info.contains("Android") || info.contains("iPhone")){
            return true;
        }
        return false;
    }
}
