package com.whli.autumn.model.system;

import com.baomidou.mybatisplus.annotation.TableName;
import com.whli.autumn.core.web.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>用户角色数据模型</p>
 * @author whli
 * @version 1.0.0
 * @since 2019-09-01 17:13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("ts_user_role")
@ApiModel(description = "用户角色")
public class UserRole extends BaseEntity{
	/**   用户主键 */
	@ApiModelProperty(value = "用户主键")
	private String userId;

	/**   角色主键 */
	@ApiModelProperty(value = "角色主键")
	private String roleId;

}
