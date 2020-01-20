package com.whli.autumn.model.system;

import com.baomidou.mybatisplus.annotation.TableName;
import com.whli.autumn.core.web.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>系统日志数据模型</p>
 * @author whli
 * @version 1.0.0
 * @since 2019-09-01 17:13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("ts_operation_log")
@ApiModel(description = "系统日志")
public class OperationLog extends BaseEntity{
	/**   操作类型(增加、修改、删除) */
	@ApiModelProperty(value = "操作类型(增加、修改、删除)")
	private String opertionType;

	/**   被操作的表 */
	@ApiModelProperty(value = "被操作的表")
	private String tableName;

	/**   操作详细信息 */
	@ApiModelProperty(value = "操作详细信息")
	private String operationDetail;

	/**   请求URI */
	@ApiModelProperty(value = "请求URI")
	private String requestUri;

	/**   操作ip */
	@ApiModelProperty(value = "操作ip")
	private String ip;

}
