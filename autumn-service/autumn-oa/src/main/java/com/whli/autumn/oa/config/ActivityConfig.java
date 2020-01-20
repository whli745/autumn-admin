package com.whli.autumn.oa.config;

import com.whli.autumn.oa.custom.CustomGroupEntityManagerFactory;
import com.whli.autumn.oa.custom.CustomUserEntityManagerFactory;
import org.activiti.engine.impl.interceptor.SessionFactory;
import org.activiti.spring.boot.ProcessEngineConfigurationConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Activity配置类</p>
 * @author whli
 * @version 1.0.0
 * @since 19-10-5 下午1:48
 */
@Configuration
public class ActivityConfig {

    @Bean
    public ProcessEngineConfigurationConfigurer processEngineConfigurationConfigurer(){

        return processEngineConfiguration -> {
            processEngineConfiguration.setActivityFontName("宋体");
            processEngineConfiguration.setLabelFontName("宋体");
            processEngineConfiguration.setAnnotationFontName("宋体");
            processEngineConfiguration.setIdGenerator(new UuidGenerator());

            List<SessionFactory> mySession = new ArrayList<>();
            mySession.add(new CustomUserEntityManagerFactory());
            mySession.add(new CustomGroupEntityManagerFactory());
            processEngineConfiguration.setCustomSessionFactories(mySession);
        };
    }
}
