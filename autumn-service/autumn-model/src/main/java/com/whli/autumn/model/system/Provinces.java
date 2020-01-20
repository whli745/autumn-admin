package com.whli.autumn.model.system;

import com.baomidou.mybatisplus.annotation.TableName;
import com.whli.autumn.core.web.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>区域数据模型</p>
 * @author whli
 * @version 1.0.0
 * @since 2019-09-01 17:13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("ts_provinces")
@ApiModel(description = "区域")
public class Provinces extends BaseEntity{

	/**   父级id */
	@ApiModelProperty(value = "父级id")
	private String parentId;

	/**   城市名称 */
	@ApiModelProperty(value = "城市名称")
	private String cityName;

	/**   城市缩写名称 */
	@ApiModelProperty(value = "城市缩写名称")
	private String shortName;

	/**   城市层级 */
	@ApiModelProperty(value = "城市层级")
	private Integer depth;

	/**   城市代码 */
	@ApiModelProperty(value = "城市代码")
	private String cityCode;

	/**   城市邮编 */
	@ApiModelProperty(value = "城市邮编")
	private String zipCode;

	/**   城市组合名称 */
	@ApiModelProperty(value = "城市组合名称")
	private String mergerName;

	/**   精度 */
	@ApiModelProperty(value = "精度")
	private String longitude;

	/**   维度 */
	@ApiModelProperty(value = "维度")
	private String latitude;

	/**   城市拼音 */
	@ApiModelProperty(value = "城市拼音")
	private String pinyin;

	/**   use */
	@ApiModelProperty(value = "启用")
	private Integer enabled;

}
