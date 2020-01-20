package com.whli.autumn.oa.service.impl;

import com.whli.autumn.core.util.WebUtils;
import com.whli.autumn.oa.entity.CustomTask;
import com.whli.autumn.oa.service.ICustomTaskService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>类描述</p>
 * @author whli
 * @version 1.0.0
 * @date 2019/6/26 10:09
 */

@Service("customTaskService")
public class CustomTaskServiceImpl implements ICustomTaskService {

    @Autowired
    private TaskService taskService;
    @Autowired
    private HistoryService historyService;

    @Override
    public List<Task> listNotDoneByPage(CustomTask entity) {
        int firstResult = (entity.getPageNumber()-1)*entity.getPageSize();
        int maxResult = entity.getPageSize();
        //参与者，个人或组任务
        List<Task> tasks = taskService
                .createTaskQuery()
                .taskCandidateOrAssigned(WebUtils.getCurrentUserId()+"")
                .active()
                .orderByTaskCreateTime()
                .desc()
                .listPage(firstResult,maxResult);
        return tasks;
    }

    @Override
    public int getNotDoneCount() {
        //参与者，个人或组任务
        long count = taskService
                .createTaskQuery()
                .taskCandidateOrAssigned(WebUtils.getCurrentUserId()+"")
                .active()
                .count();
        return new Long(count).intValue();
    }

    @Override
    public List<HistoricTaskInstance> listDoneByPage(CustomTask entity) {
        int firstResult = (entity.getPageNumber()-1)*entity.getPageSize();
        int maxResult = entity.getPageSize();

        List<HistoricTaskInstance> historicTaskInstances = historyService.createHistoricTaskInstanceQuery()
                .taskAssignee(WebUtils.getCurrentUserId()+"")
                .orderByTaskCreateTime()
                .desc()
                .listPage(firstResult, maxResult);
        return historicTaskInstances;
    }

    @Override
    public int getDoneCount() {
        long count = historyService.createHistoricTaskInstanceQuery()
                .taskAssignee(WebUtils.getCurrentUserId() + "")
                .count();
        return new Long(count).intValue();
    }

    @Override
    public int completeTask(String taskId) {
        taskService.complete(taskId);
        return 1;
    }

    @Override
    public int completeTask(String taskId, Map<String, Object> param) {
        taskService.complete(taskId,param);
        return 1;
    }
}
