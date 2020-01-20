package com.whli.autumn.model.system;

import com.baomidou.mybatisplus.annotation.TableName;
import com.whli.autumn.core.web.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>序列规则明细数据模型</p>
 * @author whli
 * @version 1.0.0
 * @since 2019-09-01 17:13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("ts_sequence_detail")
@ApiModel(description = "序列规则明细")
public class SequenceDetail extends BaseEntity{
	/**   序列规则主键 */
	@ApiModelProperty(value = "序列规则主键")
	private String sequenceId;

	/**   类型（0 字符 1 数字 2 日期） */
	@ApiModelProperty(value = "类型（0 字符 1 数字 2 日期）")
	private String type;

	/**   值 */
	@ApiModelProperty(value = "值")
	private String value;

	/**   排序 */
	@ApiModelProperty(value = "排序")
	private Integer sort;

	/**   长度 */
	@ApiModelProperty(value = "长度")
	private Integer length;

	/**   日期格式化 */
	@ApiModelProperty(value = "日期格式化")
	private String format;

	/**   分隔符 */
	@ApiModelProperty(value = "分隔符")
	private String delimit;

}
