package com.whli.autumn.auth.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.whli.autumn.core.constant.SysConstants;
import com.whli.autumn.core.share.ResponseBean;
import com.whli.autumn.core.util.JacksonUtils;
import com.whli.autumn.core.util.RedisUtils;
import com.whli.autumn.core.util.WebUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>实现Spring Security验证失败接口</p>
 * @author whli
 * @version 1.0.0
 * @since 2019/9/28 14:03
 */
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        String token = request.getHeader(SysConstants.AUTHORIZATION);
        if (token == null){
            expiredToken(response);
            return;
        }

        boolean flag = RedisUtils.exists(token);
        if (!flag){
            expiredToken(response);
            return;
        }

        String loginId = (String) RedisUtils.hGet(token,SysConstants.LOGIN_USER_ID);

        if (StringUtils.isBlank(loginId)){
            expiredToken(response);
            return;
        }

        String oldToken = (String) RedisUtils.hGet(SysConstants.KICK_OUT,loginId);
        if (StringUtils.isNotBlank(oldToken) && !token.equals(oldToken)){
            onceLogin(response);
        }
    }


    private void expiredToken(HttpServletResponse response) throws JsonProcessingException {
        ResponseBean responseBean = new ResponseBean();
        responseBean.setCode(SysConstants.TOKEN_FAIL_CODE);
        responseBean.setMessage(SysConstants.TOKEN_FAIL_MESSAGE);
        WebUtils.returnJson(response, JacksonUtils.pojoToJson(responseBean));
    }

    private void onceLogin(HttpServletResponse response) throws JsonProcessingException {
        ResponseBean responseBean = new ResponseBean();
        responseBean.setCode(SysConstants.KICK_OUT_CODE);
        responseBean.setMessage(SysConstants.KICK_OUT_MESSAGE);
        WebUtils.returnJson(response, JacksonUtils.pojoToJson(responseBean));
    }

}
