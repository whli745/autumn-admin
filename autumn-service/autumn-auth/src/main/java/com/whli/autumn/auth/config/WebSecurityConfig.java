package com.whli.autumn.auth.config;

import com.whli.autumn.auth.security.CustomAuthenticationEntryPoint;
import com.whli.autumn.auth.security.CustomTokenFilter;
import com.whli.autumn.auth.security.CustomUserService;
import com.whli.autumn.core.constant.SysConstants;
import com.whli.autumn.core.util.PwdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsUtils;

/**
 * <p>Spring Security配置</p>
 * @author whli
 * @version 1.0.0
 * @since 2019/9/28 12:51
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${app.matchers}")
    private String appMatchers;


    @Autowired
    private CustomUserService customUserService;

    @Autowired
    private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    @Autowired
    private CustomTokenFilter customTokenFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        //校验用户
        auth.userDetailsService(customUserService).passwordEncoder( new PasswordEncoder() {

            //对密码进行加密
            @Override
            public String encode(CharSequence charSequence) {
                return PwdUtils.md5Encode(charSequence.toString(), SysConstants.MY_SALT);
            }

            //对密码进行判断匹配
            @Override
            public boolean matches(CharSequence charSequence, String s) {
                String encode = PwdUtils.md5Encode(charSequence.toString(), SysConstants.MY_SALT);
                return s.equals(encode);
            }

        } );
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.cors().and().csrf().disable()
                //因为使用JWT，所以不需要HttpSession
                .sessionManagement().sessionCreationPolicy( SessionCreationPolicy.STATELESS)
            .and()
                .authorizeRequests()
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                //OPTIONS请求全部放行
                .antMatchers( HttpMethod.OPTIONS, "/**").permitAll()
                //登录接口放行
                .antMatchers(appMatchers.split(",")).permitAll()
                //其他接口全部接受验证
                .anyRequest().authenticated()
            .and()
                .exceptionHandling().authenticationEntryPoint(customAuthenticationEntryPoint);

        //使用自定义的 Token过滤器 验证请求的Token是否合法
        http.addFilterBefore(customTokenFilter, UsernamePasswordAuthenticationFilter.class);
        http.headers().cacheControl();
    }

    /**
     *  解决 无法直接注入 AuthenticationManager
     * @return
     * @throws Exception
     */
    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
