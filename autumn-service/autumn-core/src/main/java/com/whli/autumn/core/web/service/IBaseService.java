package com.whli.autumn.core.web.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.whli.autumn.core.web.entity.BaseEntity;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * <p>通用业务</p>
 * @author  whli
 * @version 1.0.0
 * @since  2019/6/23 11:51
 */
public interface IBaseService<T extends BaseEntity>{

    /**
     * 分页查询
     * @param entity T
     * @param pageNum 页码
     * @param pageSize 每页条数
     * @return com.baomidou.mybatisplus.core.metadata.IPage
     */
    IPage<T> listByPage(T entity, Integer pageNum, Integer pageSize);


    /**
     * 分页查询，返回KEY-VALUE形式的结果
     * @param entity T
     * @param pageNum 页码
     * @param pageSize 每页条数
     * @return com.baomidou.mybatisplus.core.metadata.IPage
     */
    IPage<Map<String,Object>> listMapByPage(T entity, Integer pageNum, Integer pageSize);

    /**
     * 根据主键查询
     * @param id java.io.Serializable
     * @return T
     */
    T getByPK(Serializable id);

    /**
     * 根据主键批量查询
     * @param ids java.util.List<? extends Serializable>
     * @return java.util.List<T>
     */
    List<T> listByPKs(List<? extends Serializable> ids);

    /**
     * 查询唯一值
     * @param entity T
     * @return T
     */
    T getOne(T entity);

    /**
     * 查询所有(可带参数)
     * @param entity T
     * @return java.util.List<T>
     */
    List<T> listAll(T entity);

    /**
     * 查询所有(可带参数)，返回KEY-VALUE形式的结果
     * @param entity T
     * @return java.util.List<Map<String,Object>>
     */
    List<Map<String,Object>> listMapAll(T entity);

    /**
     * 单个新增
     * @param entity T
     * @return java.lang.int
     */
    @Transactional
    int save(T entity);

    /**
     * 批量新增
     * @param entities java.util.List<T>
     * @return java.lang.int
     */
    @Transactional
    int saveBatch(List<T> entities);

    /**
     * 根据主键修改
     * @param entity T
     * @return java.lang.int
     */
    @Transactional
    int updateByPK(T entity);

    /**
     * 根据主键批量修改
     * @param entities java.util.List<T>
     * @return java.lang.int
     */
    @Transactional
    int updateBatchByPK(List<T> entities);

    /**
     * 根据主键删除
     * @param id java.io.Serializable
     * @return java.lang.int
     */
    @Transactional
    int deleteByPK(Serializable id);

    /**
     * 根据主键批量删除
     * @param ids java.util.List<? extends Serializable>
     * @return java.lang.int
     */
    @Transactional
    int deleteBatchByPK(List<? extends Serializable> ids);

    /**
     * 根据用户给定条件删除
     * @param entity T
     * @return java.lang.int
     */
    @Transactional
    int delete(T entity);

    /**
     * 下载
     * @param entity T
     * @param response 响应对象
     */
    @Transactional
    void download(T entity, HttpServletResponse response);

    /**
     * 上传文件
     * @param file java.io.File
     * @return java.lang.int
     */
    @Transactional
    int upload(File file);

    /**
     * 上传文件
     * @param contentType java.lang.String 上传文件的类型
     * @param file java.io.File
     * @return java.lang.int
     */
    @Transactional
    int upload(String contentType, File file);

    /**
     * 上传文件
     * @param stream java.io.InputStream
     * @return java.lang.int
     */
    @Transactional
    int upload(InputStream stream);

    /**
     * 上传文件
     * @param contentType java.lang.String
     * @param stream java.io.InputStream
     * @return java.lang.int
     */
    @Transactional
    int upload(String contentType, InputStream stream);
}
