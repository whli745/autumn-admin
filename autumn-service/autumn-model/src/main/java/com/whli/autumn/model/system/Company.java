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
 * <p>公司数据模型</p>
 * @author whli
 * @version 1.0.0
 * @since 2019-09-01 17:13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("ts_company")
@ApiModel(description = "公司")
public class Company extends BaseEntity{
	/**   父级编号 */
	@ApiModelProperty(value = "父级主键")
	private String parentId;

	/**   名称 */
	@ApiModelProperty(value = "名称")
	private String name;

	/**   排序 */
	@ApiModelProperty(value = "排序")
	private Integer sort;

	/**   机构类型 */
	@ApiModelProperty(value = "机构类型")
	private String type;

	/**   负责人 */
	@ApiModelProperty(value = "负责人")
	private String master;

	/**   电话 */
	@ApiModelProperty(value = "电话")
	private String phone;

	/**   传真 */
	@ApiModelProperty(value = "传真")
	private String fax;

	/**   邮箱 */
	@ApiModelProperty(value = "邮箱")
	private String email;

	/**   副负责人 */
	@ApiModelProperty(value = "副负责人")
	private String deputyPerson;

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
