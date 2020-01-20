package com.whli.autumn.job.service;

import com.whli.autumn.core.web.service.IBaseService;
import com.whli.autumn.model.job.SysJob;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>定时任务业务<p>
 * @author whli
 * @version 2018/12/24 11:15
 */
public interface ISysJobService extends IBaseService<SysJob> {

    /**
     * 立即执行JOB
     * @param entity
     * @return
     */
    @Transactional
    public  boolean triggerJob(SysJob entity);

    /**
     * 恢复执行任务
     * @param entity
     * @return
     */
    @Transactional
    public boolean resumeJob(SysJob entity);

    /**
     * 暂停任务
     * @param entity
     * @return
     */
    @Transactional
    public boolean pauseJob(SysJob entity);

    /**
     * 恢复执行所有任务
     * @return
     */
    @Transactional
    public boolean resumeAll();

    /**
     * 暂停所有任务
     * @return
     */
    @Transactional
    public boolean pauseAll();
}
