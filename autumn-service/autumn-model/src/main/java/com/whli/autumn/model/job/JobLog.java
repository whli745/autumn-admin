package com.whli.autumn.model.job;

import com.baomidou.mybatisplus.annotation.TableName;
import com.whli.autumn.core.web.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * <p>定时任务日志数据模型</p>
 * @author whli
 * @version 1.0.0
 * @since 2019-09-01 17:13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("ts_job_log")
@ApiModel(description = "定时任务日志")
public class JobLog extends BaseEntity {
	/**   任务名称 */
	@ApiModelProperty(value = "任务名称")
	private String jobName;

	/**   任务组 */
	@ApiModelProperty(value = "任务组")
	private String jobGroup;

	/**   任务类 */
	@ApiModelProperty(value = "任务类")
	private String jobClass;

	/**   运行时间 */
	@ApiModelProperty(value = "运行时间")
	private Date execTime;

	/**   总耗时（秒） */
	@ApiModelProperty(value = "总耗时（秒）")
	private Long duration;

	/**   任务运行信息 */
	@ApiModelProperty(value = "任务运行信息")
	private String jobMsg;

}
