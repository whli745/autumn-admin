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
 * <p>字典数据模型</p>
 * @author whli
 * @version 1.0.0
 * @since 2019-09-01 17:13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("ts_dict")
@ApiModel(description = "字典")
public class Dict extends BaseEntity {
	/**   父级编号 */
	@ApiModelProperty(value = "父级主键")
	private String parentId;

	/**   数据值 */
	@ApiModelProperty(value = "数据值")
	private String code;

	/**   标签名 */
	@ApiModelProperty(value = "标签名")
	private String name;

	/**   sort */
	@ApiModelProperty(value = "sort")
	private Integer sort;

	/**   是否前端可编辑 */
	@ApiModelProperty(value = "是否前端可编辑")
	private String edit;

	/**   是否启用 */
	@ApiModelProperty(value = "是否启用")
	private String enabled;

	/**   备注信息 */
	@ApiModelProperty(value = "备注信息")
	private String remark;

	/**   逻辑删除标识 */
	@TableLogic
	@ApiModelProperty(value = "逻辑删除")
	private Integer deleted;
}
