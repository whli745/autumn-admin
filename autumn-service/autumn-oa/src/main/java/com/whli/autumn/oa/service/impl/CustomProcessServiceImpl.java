package com.whli.autumn.oa.service.impl;

import com.whli.autumn.core.exception.BusinessException;
import com.whli.autumn.oa.entity.CustomProcess;
import com.whli.autumn.oa.service.ICustomProcessService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

/**
 * <p>流程管理</p>
 * @author whli
 * @date 2019/1/25 14:09
 */
@Service(value = "customProcessService")
public class CustomProcessServiceImpl implements ICustomProcessService {

    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private RuntimeService runtimeService;

    @Override
    public List<CustomProcess> listByPage(CustomProcess entity) {
        List<ProcessDefinition> processes = new ArrayList<ProcessDefinition>();
        int firstResult = (entity.getPageNumber()-1)*entity.getPageSize();
        int maxResult = entity.getPageSize();

        ProcessDefinitionQuery query = repositoryService.createProcessDefinitionQuery();
        if (StringUtils.isNotBlank(entity.getKey()) && StringUtils.isNotBlank(entity.getName())){
            processes = query.processDefinitionKeyLike(entity.getKey())
                    .processDefinitionNameLike(entity.getName())
                    .orderByProcessDefinitionVersion().desc()
                    .latestVersion().listPage(firstResult,maxResult);
        }else if (StringUtils.isNotBlank(entity.getKey())){
            processes = query.processDefinitionKeyLike(entity.getKey())
                    .orderByProcessDefinitionVersion().desc()
                    .latestVersion().listPage(firstResult,maxResult);
        }else if (StringUtils.isNotBlank(entity.getName())){
            processes = query.processDefinitionNameLike(entity.getName())
                    .orderByProcessDefinitionVersion().desc()
                    .latestVersion().listPage(firstResult,maxResult);
        }

        processes = query.latestVersion().listPage(firstResult,maxResult);
        return transToBasProcess(processes);
    }

    @Override
    public List<CustomProcess> listAll() {
        List<ProcessDefinition> processes = repositoryService.createProcessDefinitionQuery()
                .latestVersion().list();
        return transToBasProcess(processes);
    }

    @Override
    public int getCount(CustomProcess entity) {
        ProcessDefinitionQuery query = repositoryService.createProcessDefinitionQuery();
        if (StringUtils.isNotBlank(entity.getKey()) && StringUtils.isNotBlank(entity.getName())){
            return new Long(query.processDefinitionKeyLike(entity.getKey())
                    .processDefinitionNameLike(entity.getName())
                    .latestVersion().count()).intValue();
        }else if (StringUtils.isNotBlank(entity.getKey())){
            return new Long(query.processDefinitionKeyLike(entity.getKey())
                    .latestVersion().count()).intValue();
        }else if (StringUtils.isNotBlank(entity.getName())){
            return new Long(query.processDefinitionNameLike(entity.getName()).count()).intValue();
        }
        return new Long(query.latestVersion().count()).intValue();
    }

    @Override
    public int deleteProcess(String key) {
        int rows = 0;
        // 先使用流程定义的key查询流程定义，查询出所有的版本
        List<ProcessDefinition> processDefinitions = repositoryService.createProcessDefinitionQuery()
                                        .processDefinitionKey(key)
                                        .list();// 使用流程定义的key查询
        if (CollectionUtils.isNotEmpty(processDefinitions)){
            for (ProcessDefinition processDefinition : processDefinitions){
                repositoryService.deleteDeployment(processDefinition.getDeploymentId(),true);
                rows +=1;
            }
        }
        return rows;
    }

    @Override
    public int uploadProcess(String name,String key,InputStream stream) {
        if (StringUtils.isBlank(name) || StringUtils.isBlank(key)){
            throw new BusinessException("流程名或流程标识不能为空");
        }

        ZipInputStream zipInputStream = new ZipInputStream(stream);
        Deployment deployment = repositoryService.createDeployment()
                                                .name(name)
                                                .addZipInputStream(zipInputStream)
                                                .deploy();
        if (deployment != null){
            return 1;
        }

        return 0;
    }

    @Override
    public void getResourceByDeployment(String processDefinitionId, String resourceType, HttpServletResponse response) {
        InputStream resourceStream = null;
        OutputStream outputStream = null;
        try {
            ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                    .processDefinitionId(processDefinitionId).singleResult();

            String resourceName = processDefinition.getDiagramResourceName();

            if (StringUtils.isNotBlank(resourceType) && "xml".equals(resourceType)) {
                resourceName = processDefinition.getResourceName();
            }

            resourceStream = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(),
                    resourceName);
            outputStream = response.getOutputStream();

            byte[] b = new byte[1024];
            int len = -1;
            while ((len = resourceStream.read(b, 0, 1024)) != -1) {
                outputStream.write(b, 0, len);
            }
            outputStream.flush();
        } catch (IOException e) {
            throw new BusinessException("查询资源文件失败：",e);
        }finally {
            try {
                if (outputStream !=null){
                    outputStream.close();
                }
                if (resourceStream != null){
                    resourceStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public int activateProcessDefinition(String id) {
        if (StringUtils.isBlank(id)){
            throw new BusinessException("请选择需要激活的流程！");
        }
        repositoryService.activateProcessDefinitionById(id,true,null);
        return 1;
    }

    @Override
    public int suspendProcessDefinition(String id) {
        if (StringUtils.isBlank(id)){
            throw new BusinessException("请选择需要挂起的流程！");
        }
        repositoryService.suspendProcessDefinitionById(id,true,null);
        return 1;
    }

    @Override
    public String startProcessInstance(String key) {
        ProcessInstance instance = runtimeService.startProcessInstanceByKey(key);
        if (instance != null){
            return instance.getId();
        }
        return null;
    }

    @Override
    public String startProcessInstance(String key, String businessKey) {
        ProcessInstance instance = runtimeService.startProcessInstanceByKey(key,businessKey);
        if (instance != null){
            return instance.getId();
        }
        return null;
    }

    @Override
    public String startProcessInstance(String key, String businessKey, Map<String, Object> param) {
        ProcessInstance instance = runtimeService.startProcessInstanceByKey(key,businessKey,param);
        if (instance != null){
            return instance.getId();
        }
        return null;
    }

    /**
     * 内部数据转换
     * @param processes
     * @return
     */
    private List<CustomProcess> transToBasProcess(List<ProcessDefinition> processes){
        List<CustomProcess> processList = new ArrayList();
        for (ProcessDefinition pro : processes) {
            CustomProcess processModel = new CustomProcess();
            processModel.setDeploymentId(pro.getDeploymentId());
            processModel.setId(pro.getId());
            processModel.setKey(pro.getKey());
            processModel.setResourceName(pro.getResourceName());
            processModel.setVersion(pro.getVersion());
            processModel.setName(pro.getName());
            processModel.setDiagramResourceName(pro
                    .getDiagramResourceName());
            processList.add(processModel);
        }
        return processList;
    }
}
