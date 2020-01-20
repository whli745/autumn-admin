package com.whli.autumn.job.common;

import com.whli.autumn.job.service.IJobLogService;
import com.whli.autumn.model.job.JobLog;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;

import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.Date;

/**
 * <p>类功能<p>
 * @author whli
 * @version 2018/12/24 14:45
 */
public abstract class AbstractBaseJob implements Job, Serializable {
	private static final long serialVersionUID = -8163583464122658854L;

	@Autowired
    private IJobLogService jobLogService;
    @Autowired
    private PlatformTransactionManager platformTransactionManager;
    @Autowired
    private TransactionDefinition transactionDefinition;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        String jobName = context.getTrigger().getJobKey().getName();
        String jobGroup = context.getTrigger().getJobKey().getGroup();
        String className = this.getClass().getName();
        Date startTime = new Date();

        logger.info("【"+jobName+"】 任务运行开始！");
        TransactionStatus transactionStatus = platformTransactionManager.getTransaction(transactionDefinition);
        try {
            processJob();
            createJobLog(jobName,jobGroup,className,"【"+jobName+"】 任务运行成功！",startTime);
            platformTransactionManager.commit(transactionStatus);

            logger.info("【"+jobName+"】 任务运行成功！");

        }catch (Exception e){
            platformTransactionManager.rollback(transactionStatus);

            StringWriter stringWriter = new StringWriter();
            e.printStackTrace(new PrintWriter(stringWriter));
            String message = "【"+jobName+"】 任务运行失败："+stringWriter.toString();

            createJobLog(jobName,jobGroup,className,message,startTime);

            logger.error("【"+jobName+"】 任务运行失败：",e);

            throw new JobExecutionException(e);
        }

        logger.info("【"+jobName+"】 任务运行结束！");
        logger.info("总耗时(秒)："+((new Date().getTime()-startTime.getTime())/1000));
    }

    public abstract void processJob() throws Exception;

    private void createJobLog(String jobName,String jobGroup,String className,String message,Date startTime){
        JobLog jobLog = new JobLog();
        jobLog.setJobName(jobName);
        jobLog.setJobGroup(jobGroup);
        jobLog.setJobClass(className);
        jobLog.setExecTime(startTime);
        jobLog.setJobMsg(message);
        jobLog.setDuration((new Date().getTime()-startTime.getTime())/1000);
        jobLogService.save(jobLog);
    }
}
