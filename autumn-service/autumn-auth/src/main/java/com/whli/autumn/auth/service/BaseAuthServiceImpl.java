package com.whli.autumn.auth.service;

import com.whli.autumn.auth.security.UserPrincipal;
import com.whli.autumn.core.constant.SysConstants;
import com.whli.autumn.core.exception.BusinessException;
import com.whli.autumn.core.util.BeanUtils;
import com.whli.autumn.core.util.RedisUtils;
import com.whli.autumn.core.util.WebUtils;
import com.whli.autumn.core.web.service.IBaseAuthService;
import com.whli.autumn.system.service.IUserCompanyService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * <p>类描述</p>
 * @author whli
 * @version 1.0.0
 * @since 2019/9/28 20:01
 */
@Service("baseAuthService")
public class BaseAuthServiceImpl implements IBaseAuthService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private IUserCompanyService userCompanyService;

    @Value("${app.expiration}")
    private String expiration;

    @Override
    public Map<String, Object> login(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String companyId = request.getParameter("companyId");

        if(StringUtils.isBlank(username) || StringUtils.isBlank(password)){
            throw new BusinessException("用户名或密码不能为空");
        }

        if (StringUtils.isBlank(companyId)){
            throw new BusinessException("公司不能为空");
        }

        Map<String,Object> result = new HashMap<>();
        try {
            UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username,password );
            Authentication authentication = authenticationManager.authenticate(upToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

            if (!"1".equals(userPrincipal.getSuperAdmin())){
                List<String> companyIds = userCompanyService.listCompanyIdByUserId(userPrincipal.getId());
                if (!companyIds.contains(companyId)){
                    throw new BusinessException("非该公司用户，请重新选择");
                }
            }

            if ("0".equals(userPrincipal.getEnabled()) || 1 == userPrincipal.getDeleted()){
                throw new LockedException("Account Locked");
            }

            //生成Token并缓存到Redis
            String token = BeanUtils.getUUID();
            Map<String,String> field = new HashMap<>();
            field.put(SysConstants.LOGIN_USER_NAME,userPrincipal.getName());
            field.put(SysConstants.LOGIN_USER_ID,userPrincipal.getId());
            if (StringUtils.isNotBlank(companyId)){
                field.put(SysConstants.LOGIN_COMPANY_ID,companyId);
            }
            RedisUtils.multiHSet(token,field,new Long(expiration), TimeUnit.SECONDS);

            //设定同一帐号只登录一次
            if (RedisUtils.exists(SysConstants.KICK_OUT)){
                RedisUtils.hSet(SysConstants.KICK_OUT,userPrincipal.getId(),token);
            }else{
                RedisUtils.hSet(SysConstants.KICK_OUT,userPrincipal.getId(),token,new Long(expiration), TimeUnit.SECONDS);
            }

            //设定返回值
            result.put(SysConstants.LOGIN_USER_NAME,userPrincipal.getName());
            result.put(SysConstants.LOGIN_USER_ID,userPrincipal.getId());
            if (StringUtils.isNotBlank(companyId)){
                result.put(SysConstants.LOGIN_COMPANY_ID,companyId);
            }
            result.put(SysConstants.LOGIN_TOKEN,token);
        } catch (UsernameNotFoundException e){
            throw new BusinessException("用户名或密码不正确");
        } catch (BadCredentialsException e){
            throw new BusinessException("用户名或密码不正确");
        } catch (LockedException e){
            throw new BusinessException("用户禁止登录，请联系管理员");
        } catch (Exception e){
            logger.error("登录失败：{}",e);
            throw new BusinessException("用户不存在");
        }
        return result;
    }

    @Override
    public boolean logout(HttpServletRequest request) {
        String token = WebUtils.getToken();
        if (StringUtils.isNotBlank(token) && WebUtils.existsToken(token)){

            String loginId = WebUtils.getCurrentUserId();
            String oldToken = (String) RedisUtils.hGet(SysConstants.KICK_OUT,loginId);
            if (StringUtils.isNotBlank(oldToken) && token.equals(oldToken)){
                RedisUtils.hDelete(SysConstants.KICK_OUT,loginId);
            }

            return RedisUtils.delete(token);
        }
        return true;
    }
}
