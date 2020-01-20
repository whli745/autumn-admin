package com.whli.autumn.model.system;

import com.baomidou.mybatisplus.annotation.TableName;
import com.whli.autumn.core.web.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>序列规则数据模型</p>
 * @author whli
 * @version 1.0.0
 * @since 2019-09-01 17:13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("ts_sequence")
@ApiModel(description = "序列规则")
public class Sequence extends BaseEntity{
	/**   序列规则编码 */
	@ApiModelProperty(value = "序列规则编码")
	private String code;

	/**   序列规则名称 */
	@ApiModelProperty(value = "序列规则名称")
	private String name;

	/**   备注信息 */
	@ApiModelProperty(value = "备注信息")
	private String remark;

	/**   是否启用 */
	@ApiModelProperty(value = "是否启用")
	private String enabled;
}
