package com.whli.autumn.model.system;

import com.whli.autumn.core.web.entity.BaseEntity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>文件上传数据模型</p>
 * @author blog.whli745.com
 * @version 1.0.0
 * @since 2019-12-05 19:48
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("ts_upload")
@ApiModel(description = "文件上传")
public class Upload extends BaseEntity{
	/**   公司 */
	@ApiModelProperty(value = "公司")
	private java.lang.String companyId;

	/**   文件标识 */
	@ApiModelProperty(value = "文件标识")
	private java.lang.String uniqueKey;

	/**   文件名称 */
	@ApiModelProperty(value = "文件名称")
	private java.lang.String name;

	/**   文件类型 */
	@ApiModelProperty(value = "文件类型")
	private java.lang.String type;

	/**   文件路径 */
	@ApiModelProperty(value = "文件路径")
	private java.lang.String url;

	/**   强制更新 */
	@ApiModelProperty(value = "强制更新")
	private java.lang.String updated;

	/**   文件版本 */
	@ApiModelProperty(value = "文件版本")
	private java.lang.String version;

	/**   备注 */
	@ApiModelProperty(value = "备注")
	private java.lang.String remark;

}
