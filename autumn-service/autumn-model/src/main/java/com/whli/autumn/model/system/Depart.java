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
 * <p>部门数据模型</p>
 * @author whli
 * @version 1.0.0
 * @since 2019-10-26 00:20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("ts_depart")
@ApiModel(description = "部门")
public class Depart extends BaseEntity{
	/**   parentId */
	@ApiModelProperty(value = "父级主键")
	private String parentId;

	/**   所属公司 */
	@ApiModelProperty(value = "所属公司")
	private String companyId;

	/**   部门编码 */
	@ApiModelProperty(value = "部门编码")
	private java.lang.String code;

	/**   部门名称 */
	@ApiModelProperty(value = "部门名称")
	private java.lang.String name;

	/**   排序 */
	@ApiModelProperty(value = "排序")
	private java.lang.Integer sort;

	/**   是否启用 */
	@ApiModelProperty(value = "是否启用")
	private java.lang.String enabled;

	/**   备注 */
	@ApiModelProperty(value = "备注")
	private java.lang.String remark;

	/**   逻辑删除标识 */
	@TableLogic
	@ApiModelProperty(value = "逻辑删除")
	private Integer deleted;
}
