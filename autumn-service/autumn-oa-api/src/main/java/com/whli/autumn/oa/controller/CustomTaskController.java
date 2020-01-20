package com.whli.autumn.oa.controller;

import com.whli.autumn.core.share.ResponseBean;
import com.whli.autumn.oa.entity.CustomTask;
import com.whli.autumn.oa.service.ICustomTaskService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>任务控制类</p>
 * @author whli
 * @version 1.0.0
 * @date 2019/6/26 10:37
 */
@RestController
@RequestMapping(value = "/activity/customTask")
public class CustomTaskController{

    @Autowired
    private ICustomTaskService taskService;

    /**
     * 分页查询个人未办任务
     * @return
     * @throws Exception
     */
    @PostMapping("/listNotDoneByPage")
    public ResponseBean listNotDoneByPage(@RequestBody CustomTask entity) throws Exception{
        int count = taskService.getNotDoneCount();
        List<Task> tasks = taskService.listNotDoneByPage(entity);
        return ResponseBean.getInstance(0,null,tasks,count);
    }

    /**
     * 分页查询个人已办任务
     * @return
     * @throws Exception
     */
    @PostMapping("/listDoneByPage")
    public ResponseBean listDoneByPage(@RequestBody CustomTask entity) throws Exception{
        int count = taskService.getDoneCount();
        List<HistoricTaskInstance> tasks = taskService.listDoneByPage(entity);
        return ResponseBean.getInstance(0,null,tasks,count);
    }
}
