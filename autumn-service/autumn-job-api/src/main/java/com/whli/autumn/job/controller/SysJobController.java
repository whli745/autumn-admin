package com.whli.autumn.job.controller;

import com.whli.autumn.core.share.ResponseBean;
import com.whli.autumn.core.web.controller.BaseController;
import com.whli.autumn.core.web.service.IBaseService;
import com.whli.autumn.job.service.ISysJobService;
import com.whli.autumn.model.job.SysJob;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>定时任务API<p>
 * @author whli
 * @version 2018/12/24 14:04
 */
@RestController
@RequestMapping("/scheduler/task")
@Api(description = "定时任务API")
public class SysJobController extends BaseController<SysJob> {
    @Autowired
    private ISysJobService sysJobService;

    @Override
    public IBaseService<SysJob> getService() {
        return sysJobService;
    }

    /**
     * 立即执行定时任务
     * @param entity com.whli.autumn.model.job.SysJob
     * @param req
     * @return com.whli.autumn.core.share.ResponseBean
     * @throws Exception
     */
    @PostMapping("/triggerJob")
    @ApiOperation("立即执行定时任务")
    public ResponseBean triggerJob(@RequestBody SysJob entity, HttpServletRequest req) throws Exception {
        boolean rows = sysJobService.triggerJob(entity);
        if (rows) {
            return ResponseBean.success("立即执行定时任务成功");
        }
        return ResponseBean.fail("立即执行定时任务失败");
    }

    /**
     * 恢复定时任务
     * @param entity com.whli.autumn.model.job.SysJob
     * @return com.whli.autumn.core.share.ResponseBean
     */
    @PostMapping("/resume")
    @ApiOperation("恢复定时任务")
    public ResponseBean resumeJob(@RequestBody SysJob entity, HttpServletRequest req) throws Exception {
        boolean rows = sysJobService.resumeJob(entity);
        if (rows) {
            return ResponseBean.success("恢复定时任务成功");
        }
        return ResponseBean.fail("恢复定时任务失败");
    }

    /**
     * 恢复所有任务
     * @param entity com.whli.autumn.model.job.SysJob
     * @return com.whli.autumn.core.share.ResponseBean
     */
    @PostMapping("/resumeAll")
    @ApiOperation("恢复所有任务")
    public ResponseBean resumeAllJob(@RequestBody SysJob entity, HttpServletRequest req) throws Exception {
        boolean rows = sysJobService.resumeAll();
        if (rows) {
            return ResponseBean.success("恢复定时任务成功");
        }
        return ResponseBean.fail("恢复定时任务失败");
    }

    /**
     * 暂停任务
     * @param entity com.whli.autumn.model.job.SysJob
     * @return com.whli.autumn.core.share.ResponseBean
     */
    @PostMapping("/pause")
    @ApiOperation("暂停定时任务")
    public ResponseBean pauseJob(@RequestBody SysJob entity, HttpServletRequest req) throws Exception {
        boolean rows = sysJobService.pauseJob(entity);
        if (rows) {
            return ResponseBean.success("暂停定时任务成功");
        }
        return ResponseBean.fail("暂停定时任务失败");
    }

    /**
     * 暂停所有任务
     * @param entity com.whli.autumn.model.job.SysJob
     * @return com.whli.autumn.core.share.ResponseBean
     */
    @PostMapping("/pauseAll")
    @ApiOperation("暂停所有任务")
    public ResponseBean pauseAllTask(@RequestBody SysJob entity, HttpServletRequest req) throws Exception {
        boolean rows = sysJobService.pauseAll();
        if (rows) {
            return ResponseBean.success("暂停定时任务成功");
        }
        return ResponseBean.fail("暂停定时任务失败");
    }
}
