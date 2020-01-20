package com.whli.autumn.model.system;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.whli.autumn.core.web.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>角色数据模型</p>
 * @author whli
 * @version 1.0.0
 * @since 2019-09-01 17:13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("ts_role")
@ApiModel(description = "角色")
public class Role extends BaseEntity{

	/**   公司 */
	@ApiModelProperty(value = "所属公司")
	private String companyId;

	/**   角色编码 */
	@ApiModelProperty(value = "角色编码")
	private String code;

	/**   中文名称 */
	@ApiModelProperty(value = "中文名称")
	private String name;

	/**   备注信息 */
	@ApiModelProperty(value = "备注信息")
	private String remark;

	/**   是否启用 */
	@ApiModelProperty(value = "是否启用")
	private String enabled;

	/**   逻辑删除标识 */
	@TableLogic
	@ApiModelProperty(value = "逻辑删除")
	private Integer deleted;
}
