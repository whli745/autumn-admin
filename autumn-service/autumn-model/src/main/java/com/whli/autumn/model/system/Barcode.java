package com.whli.autumn.model.system;

import com.baomidou.mybatisplus.annotation.TableName;
import com.whli.autumn.core.web.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>条码生成数据模型</p>
 * @author whli
 * @version 1.0.0
 * @since 2019-09-01 17:13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("ts_barcode")
@ApiModel(description = "条码生成")
public class Barcode extends BaseEntity {
	/**   序列号规则 */
	@ApiModelProperty(value = "序列号规则")
	private String sequenceId;

	/**   条码规则号 */
	@ApiModelProperty(value = "条码规则号")
	private String code;

	/**   条码规则名称 */
	@ApiModelProperty(value = "条码规则名称")
	private String name;

	/**   是否启用 */
	@ApiModelProperty(value = "是否启用")
	private String enabled;

	/**   备注信息 */
	@ApiModelProperty(value = "备注信息")
	private String remark;

}
