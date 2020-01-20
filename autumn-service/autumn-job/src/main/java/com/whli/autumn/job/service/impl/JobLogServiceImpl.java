package com.whli.autumn.job.service.impl;

import com.whli.autumn.core.web.dao.IBaseDao;
import com.whli.autumn.core.web.service.impl.BaseServiceImpl;
import com.whli.autumn.job.dao.IJobLogDao;
import com.whli.autumn.job.service.IJobLogService;
import com.whli.autumn.model.job.JobLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>定时任务日志业务实现</p>
 * @author whli
 * @version 1.0.0
 * @since 2019-09-01 17:13
 */
@Service("jobLogService")
public class JobLogServiceImpl extends BaseServiceImpl<JobLog> implements IJobLogService {

	@Autowired
	private IJobLogDao jobLogDao;

	@Override
	public IBaseDao<JobLog> getDao() {
		return jobLogDao;
	}
}
