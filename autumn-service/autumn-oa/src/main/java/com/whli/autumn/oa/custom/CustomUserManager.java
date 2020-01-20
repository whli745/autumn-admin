package com.whli.autumn.oa.custom;

import com.whli.autumn.model.system.Role;
import com.whli.autumn.system.service.IRoleService;
import com.whli.autumn.system.service.IUserService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.activiti.engine.impl.persistence.entity.GroupEntity;
import org.activiti.engine.impl.persistence.entity.UserEntity;
import org.activiti.engine.impl.persistence.entity.UserEntityManager;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Activity自定义用户操作</p>
 * @author whli
 * @version 1.0.0
 * @date 2019/6/26 14:55
 */
@Component
public class CustomUserManager extends UserEntityManager {

    @Autowired
    private IUserService userService;
    @Autowired
    private IRoleService roleService;

    @Override
    public User findUserById(String userId) {
        if (StringUtils.isBlank(userId)){
            return null;
        }
        UserEntity entity = new UserEntity();
        com.whli.autumn.model.system.User temp = userService.getByPK(userId);
        if (temp != null){
            entity.setId(temp.getId()+"");
            entity.setFirstName(temp.getLoginName());
            entity.setLastName(temp.getUserName());
            entity.setEmail(temp.getEmail());
        }


        return entity;
    }

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
