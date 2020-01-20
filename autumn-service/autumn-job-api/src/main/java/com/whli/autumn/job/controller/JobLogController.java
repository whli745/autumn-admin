package com.whli.autumn.job.controller;

import com.whli.autumn.core.web.controller.BaseController;
import com.whli.autumn.core.web.service.IBaseService;
import com.whli.autumn.job.service.IJobLogService;
import com.whli.autumn.model.job.JobLog;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>定时任务日志API</p>
 * @author whli
 * @version 1.0.0
 * @since 2019-09-01 17:13
 */
@RestController
@RequestMapping(value="/scheduler/jobLog")
@Api(description = "定时任务日志API")
public class JobLogController extends BaseController<JobLog>{

	@Autowired
	private IJobLogService jobLogService;
	
	@Override
    public IBaseService<JobLog> getService() {
        return jobLogService;
    }
}

