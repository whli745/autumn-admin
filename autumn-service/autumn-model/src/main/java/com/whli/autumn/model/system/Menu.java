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
 * <p>菜单数据模型</p>
 * @author whli
 * @version 1.0.0
 * @since 2019-09-01 17:13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("ts_menu")
@ApiModel(description = "")
public class Menu extends BaseEntity{
	/**   父级编号 */
	@ApiModelProperty(value = "父级编号")
	private String parentId;

	/**   名称 */
	@ApiModelProperty(value = "名称")
	private String name;

	/**   排序 */
	@ApiModelProperty(value = "排序")
	private Integer sort;

	/**   链接 */
	@ApiModelProperty(value = "链接")
	private String href;

	/**   目标 */
	@ApiModelProperty(value = "目标")
	private String target;

	/**   图标 */
	@ApiModelProperty(value = "图标")
	private String menuIcon;

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
