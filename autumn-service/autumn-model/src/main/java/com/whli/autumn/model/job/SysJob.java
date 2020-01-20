package com.whli.autumn.model.job;

import com.whli.autumn.core.web.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>Quartz JobDetail<p>
 * @author whli
 * @version 2018/12/24 10:04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(description = "Quartz JobDetail")
public class SysJob extends BaseEntity {

    private String jobName; //任务名称
    private String jobGroup; //任务分组
    private String jobClass;//任务执行方法
    private String cronExpression; // cron 表达式
    private String jobDescription; //任务描述
    private Long startTime;
    private Long prevTime;
    private Long nextTime;
    private String state; //状态
}
