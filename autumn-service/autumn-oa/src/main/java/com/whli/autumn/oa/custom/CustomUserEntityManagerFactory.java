package com.whli.autumn.oa.custom;

import org.activiti.engine.impl.interceptor.Session;
import org.activiti.engine.impl.interceptor.SessionFactory;
import org.activiti.engine.impl.persistence.entity.UserEntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>Activity用户控制工厂</p>
 * @author whli
 * @version 1.0.0
 * @date 2019/6/26 15:01
 */
@Service
public class CustomUserEntityManagerFactory implements SessionFactory {
    @Autowired
    private CustomUserManager customUserManager;

    @Override
    public Class<?> getSessionType() {
        return UserEntityManager.class;
    }

    @Override
    public Session openSession() {
        return customUserManager;
    }
}
