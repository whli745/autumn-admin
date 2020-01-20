package com.whli.autumn.core.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.whli.autumn.core.util.WebUtils;
import com.whli.autumn.core.web.dao.IBaseDao;
import com.whli.autumn.core.web.entity.BaseEntity;
import com.whli.autumn.core.web.service.IBaseService;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>通用业务默认实现</p>
 * @author  whli
 * @version 1.0.0
 * @since  2019/6/23 11:51
 */
public abstract class BaseServiceImpl<T extends BaseEntity> implements IBaseService<T> {

    public abstract IBaseDao<T> getDao();

    @Override
    public IPage<T> listByPage(T entity, Integer pageNum, Integer pageSize) {
        Page<T> page = new Page<>(pageNum,pageSize);
        QueryWrapper<T> wrapper = Wrappers.query(entity);
        wrapper.orderByDesc("update_date");
        return getDao().selectPage(page,wrapper);
    }

    @Override
    public IPage<Map<String, Object>> listMapByPage(T entity, Integer pageNum, Integer pageSize) {
        Page<T> page = new Page<>(pageNum,pageSize);
        QueryWrapper<T> wrapper = Wrappers.query(entity);
        wrapper.orderByDesc("update_date");
        return getDao().selectMapsPage(page,wrapper);
    }

    @Override
    public List<T> listAll(T entity) {
        QueryWrapper<T> wrapper = Wrappers.query(entity);
        wrapper.orderByDesc("update_date");
        return getDao().selectList(wrapper);
    }

    @Override
    public List<Map<String, Object>> listMapAll(T entity) {
        QueryWrapper<T> wrapper = Wrappers.query(entity);
        wrapper.orderByDesc("update_date");
        return getDao().selectMaps(wrapper);
    }

    @Override
    public T getByPK(Serializable id) {
        return getDao().selectById(id);
    }

    @Override
    public List<T> listByPKs(List<? extends Serializable> ids) {
        return getDao().selectBatchIds(ids);
    }

    public T getOne(T entity){
        QueryWrapper<T> wrapper = Wrappers.query(entity);
        return getDao().selectOne(wrapper);
    }

    @Override
    public int save(T entity) {
        String createBy = StringUtils.isEmpty(WebUtils.getCurrentUserName()) ? "anonymous" : WebUtils.getCurrentUserName();
        entity.setCreateBy(createBy);
        entity.setCreateDate(new Date());
        entity.setUpdateBy(createBy);
        entity.setUpdateDate(new Date());
        return getDao().insert(entity);
    }

    @Override
    public int saveBatch(List<T> entities) {
        entities.forEach(entity -> save(entity));
        return 1;
    }

    @Override
    public int updateByPK(T entity) {
        String updateBy = StringUtils.isEmpty(WebUtils.getCurrentUserName()) ? "anonymous" : WebUtils.getCurrentUserName();
        entity.setUpdateBy(updateBy);
        entity.setUpdateDate(new Date());
        return getDao().updateById(entity);
    }

    @Override
    public int updateBatchByPK(List<T> entities) {
        entities.forEach(entity -> updateByPK(entity));
        return 1;
    }

    @Override
    public int deleteByPK(Serializable id) {
        return getDao().deleteById(id);
    }

    @Override
    public int deleteBatchByPK(List<? extends Serializable> ids) {
        return getDao().deleteBatchIds(ids);
    }

    @Override
    public int delete(T entity) {
        QueryWrapper<T> wrapper = Wrappers.query(entity);
        return getDao().delete(wrapper);
    }

    @Override
    public void download(T entity, HttpServletResponse response) {

    }

    @Override
    public int upload(File file) {
        return 0;
    }

    @Override
    public int upload(InputStream stream) {
        return 0;
    }

    @Override
    public int upload(String contentType, File file) {
        return 0;
    }

    @Override
    public int upload(String contentType, InputStream stream) {
        return 0;
    }
}
