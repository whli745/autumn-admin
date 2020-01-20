package com.whli.autumn.oa.custom;

import com.whli.autumn.model.system.Role;
import com.whli.autumn.system.service.IRoleService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.impl.persistence.entity.GroupEntity;
import org.activiti.engine.impl.persistence.entity.GroupEntityManager;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Activity自定义组操作</p>
 * @author whli
 * @version 1.0.0
 * @date 2019/6/26 14:02
 */
@Component
public class CustomGroupManager extends GroupEntityManager {

    @Autowired
    private IRoleService roleService;

    @Override
    public List<Group> findGroupsByUser(String userId) {
        if (userId == null){
            return null;
        }
        List<Role> roles = roleService.listByUser(userId);
        List<Group> groupEntities = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(roles)){
            for (Role role : roles){
                GroupEntity groupEntity = new GroupEntity();
                groupEntity.setId(role.getId()+"");
                groupEntity.setName(role.getCode());
                groupEntity.setRevision(1);
                groupEntity.setType("assignment");
                groupEntities.add(groupEntity);
            }
        }
        return groupEntities;
    }


}
