package com.whli.autumn.model.system;

import com.baomidou.mybatisplus.annotation.TableName;
import com.whli.autumn.core.web.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>角色菜单关系数据模型</p>
 * @author whli
 * @version 1.0.0
 * @since 2019-09-01 17:13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("ts_role_menu")
@ApiModel(description = "角色菜单关系")
public class RoleMenu extends BaseEntity{
	/**   角色主键 */
	@ApiModelProperty(value = "角色主键")
	private String roleId;

	/**   菜单主键 */
	@ApiModelProperty(value = "菜单主键")
	private String menuId;

}
