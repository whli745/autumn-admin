package com.whli.autumn.oa.custom;

import org.activiti.engine.impl.interceptor.Session;
import org.activiti.engine.impl.interceptor.SessionFactory;
import org.activiti.engine.impl.persistence.entity.GroupEntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>自定Activity用户组工厂</p>
 * @author whli
 * @version 1.0.0
 * @date 2019/6/26 14:24
 */
@Service
public class CustomGroupEntityManagerFactory implements SessionFactory {

    @Autowired
    private CustomGroupManager customGroupManager;

    @Override
    public Class<?> getSessionType() {
        // 返回原始的GroupManager类型
        return GroupEntityManager.class;
    }

    @Override
    public Session openSession() {
        return customGroupManager;
    }

}
