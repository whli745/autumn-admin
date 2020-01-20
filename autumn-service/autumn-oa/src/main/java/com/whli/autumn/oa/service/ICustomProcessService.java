package com.whli.autumn.oa.service;

import com.whli.autumn.oa.entity.CustomProcess;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * <p>类或方法描述</p>
 *
 * @author whli
 * @date 2019/1/25 14:09
 */
public interface ICustomProcessService {

    /**
     * 分页查询流程定义
     * @return
     */
    List<CustomProcess> listByPage(CustomProcess entity);

    /**
     * 分页查询流程定义
     * @return
     */
    List<CustomProcess> listAll();

    /**
     * 获取所有流程定义的总数
     * @return
     */
    int getCount(CustomProcess entity);

    /**
     * 根据流程KEY删除流程定义
     * @param key
     * @return
     */
    @Transactional
    int deleteProcess(String key);

    /**
     * 导入流程定义
     * @return
     */
    @Transactional
    int uploadProcess(String name, String key, InputStream stream);

    /**
     * 查询资源文件
     * @param processDefinitionId
     * @param resourceType
     * @param response
     */
    public void getResourceByDeployment(String processDefinitionId, String resourceType, HttpServletResponse response);

    /**
     * 激活流程定义
     * @param id
     * @return
     */
    @Transactional
    int activateProcessDefinition(String id);

    /**
     * 挂起流程定义
     * @param id
     * @return
     */
    @Transactional
    int suspendProcessDefinition(String id);

    /**
     * 根据Key启动流程
     * @param key
     * @return
     */
    @Transactional
    String startProcessInstance(String key);

    /**
     * 根据Key启动流程，并设置业务Key
     * @param key
     * @param businessKey
     * @return
     */
    @Transactional
    String startProcessInstance(String key, String businessKey);

    /**
     * 根据Key启动流程，并设置业务Key，传入参数
     * @param key
     * @param businessKey
     * @param param
     * @return
     */
    @Transactional
    String startProcessInstance(String key, String businessKey, Map<String, Object> param);
}
