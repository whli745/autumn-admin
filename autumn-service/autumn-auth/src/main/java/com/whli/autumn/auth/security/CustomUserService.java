package com.whli.autumn.auth.security;

import com.whli.autumn.model.system.User;
import com.whli.autumn.system.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * <p>自定义实现Security UserDetailsService</p>
 * @author whli
 * @version 1.0.0
 * @since 2019/9/26 10:24
 */
@Component
public class CustomUserService implements UserDetailsService {

    @Autowired
    private IUserService userService;

    @Override
    public UserDetails loadUserByUsername(String loginName) throws UsernameNotFoundException {
        User user = userService.getByLoginNameOrEmailOrPhone(loginName);
        if (user == null){
             throw new UsernameNotFoundException("User not found with username or email or phone : " + loginName);
        }
        return UserPrincipal.create(user,null);
    }
}
