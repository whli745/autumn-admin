package com.whli.autumn.job.dao;

import com.whli.autumn.core.web.dao.IBaseDao;
import com.whli.autumn.model.job.JobLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>定时任务日志数据操作</p>
 * @author whli
 * @version 1.0.0
 * @since 2019-09-01 17:13
 */
@Mapper
public interface IJobLogDao extends IBaseDao<JobLog> {

}
