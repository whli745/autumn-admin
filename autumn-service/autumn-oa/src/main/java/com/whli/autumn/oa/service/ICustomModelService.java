package com.whli.autumn.oa.service;

import com.whli.autumn.oa.entity.CustomModel;
import org.activiti.engine.repository.Model;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>流程模型业务层</p>
 * @author whli
 * @date 2019/1/16 10:27
 */
public interface ICustomModelService {

    /**
     * 分页查询流程模型
     * @return
     */
    public List<Model>  listByPage(CustomModel entity);

    /**
     * 分页查询流程模型
     * @return
     */
    public List<Model>  listAll();

    /**
     * 获取所有模型的总数
     * @return
     */
    public int getCount(CustomModel entity);

    /**
     * 创建新模型
     * @param entity
     */
    @Transactional
    public String addModel(CustomModel entity);

    /**
     * 删除模型
     * @param entity
     * @return
     */
    @Transactional
    public int deleteModel(CustomModel entity);

    /**
     * 发布模型
     * @param entity
     * @return
     */
    @Transactional
    public int deployModel(CustomModel entity);
}
