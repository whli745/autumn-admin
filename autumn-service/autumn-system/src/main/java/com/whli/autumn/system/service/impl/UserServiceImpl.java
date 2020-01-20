package com.whli.autumn.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.whli.autumn.core.constant.SysConstants;
import com.whli.autumn.core.exception.BusinessException;
import com.whli.autumn.core.util.PwdUtils;
import com.whli.autumn.core.web.dao.IBaseDao;
import com.whli.autumn.core.web.service.impl.BaseServiceImpl;
import com.whli.autumn.model.system.Company;
import com.whli.autumn.model.system.User;
import com.whli.autumn.model.system.UserCompany;
import com.whli.autumn.system.dao.IUserDao;
import com.whli.autumn.system.dto.UserDTO;
import com.whli.autumn.system.service.IUserCompanyService;
import com.whli.autumn.system.service.IUserService;
import com.whli.autumn.system.util.SystemUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>用户业务实现</p>
 * @author whli
 * @version 1.0.0
 * @since 2019-09-01 17:13
 */
@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements IUserService {

    @Autowired
    private IUserDao userDao;
    @Autowired
    private IUserCompanyService userCompanyService;

    @Override
    public IBaseDao<User> getDao() {
        return userDao;
    }

    @Override
    public User getByLoginNameOrEmailOrPhone(String loginName) {
        return userDao.getByLoginNameOrEmailOrPhone(loginName);
    }

    @Override
    public User getByUnionId(String unionId) {
        return userDao.getByUnionId(unionId);
    }

    @Override
    public IPage<User> listUserByPage(UserDTO entity, Integer pageNum, Integer pageSize) {
        Page<User> page = new Page<User>(pageNum,pageSize);
        page.setRecords(userDao.listUserByPage(page,entity));
        return page;
    }

    @Override
    public int saveUser(UserDTO entity) {

        User temp = new User();
        BeanUtils.copyProperties(entity,temp);
        temp.setPassword(PwdUtils.md5Encode(SysConstants.DEFAULT_PASSWORD, SysConstants.MY_SALT));

        int rows = super.save(temp);
        if (rows > 0){
            List<UserCompany> UserCompanies = new ArrayList<>();
            //增加用户与公司对应关系
            if(StringUtils.isNotBlank(entity.getCompanyId())){
                UserCompany userCompany = new UserCompany();
                userCompany.setUserId(temp.getId());
                userCompany.setCompanyId(entity.getCompanyId());
                UserCompanies.add(userCompany);
            }

            Company rootCompany = SystemUtils.getRootCompany();
            if (rootCompany != null  && rootCompany.getId().compareTo(entity.getCompanyId()) != 0){
                UserCompany root = new UserCompany();
                root.setUserId(temp.getId());
                root.setCompanyId(rootCompany.getId());
                UserCompanies.add(root);
            }

            if (!CollectionUtils.isEmpty(UserCompanies)){
                rows += userCompanyService.saveBatch(UserCompanies);
            }

        }
        return rows;
    }

    @Override
    public int updateEnabledByPks(UserDTO entity) {
        return userDao.updateEnabledByPks(entity);
    }

    @Override
    public int resetPassword(List<String> ids) {
        if (CollectionUtils.isEmpty(ids)){
            throw new BusinessException("请选择需要重置密码的数据");
        }

        ids.forEach(id -> {
            User user = this.getByPK(id);
            user.setPassword(PwdUtils.md5Encode(SysConstants.DEFAULT_PASSWORD,SysConstants.MY_SALT));
            this.updateByPK(user);
        });

        return 1;
    }

    @Override
    public int updatePassword(User entity) {
        entity.setPassword(PwdUtils.md5Encode(entity.getPassword(),SysConstants.MY_SALT));
        return this.updateByPK(entity);
    }

    @Override
    public User validate(User entity) {
        return userDao.validate(entity);
    }
}
