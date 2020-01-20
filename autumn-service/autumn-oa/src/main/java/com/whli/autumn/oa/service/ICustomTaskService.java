package com.whli.autumn.oa.service;

import com.whli.autumn.oa.entity.CustomTask;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.task.Task;

import java.util.List;
import java.util.Map;

/**
 * <p>审批任务查询</p>
 * @author whli
 * @version 1.0.0
 * @date 2019/6/26 10:07
 */
public interface ICustomTaskService {

    /**
     * <p>分页查询个人未办任务</p>
     * @param entity
     * @return
     */
    List<Task> listNotDoneByPage(CustomTask entity);

    /**
     * <p>查询个人未办任务数量</p>
     */
    int getNotDoneCount();

    /**
     * <p>分页查询个人已办任务</p>
     * @param entity
     * @return
     */
    List<HistoricTaskInstance> listDoneByPage(CustomTask entity);

    /**
     * <p>查询个人未办任务数量</p>
     */
    int getDoneCount();

    /**
     * 完成任务
     * @param taskId
     * @return
     */
    int completeTask(String taskId);

    /**
     * 完成任务
     * @param taskId
     * @return
     */
    int completeTask(String taskId, Map<String, Object> param);
}
