package com.whli.autumn.model.system;

import com.baomidou.mybatisplus.annotation.TableName;
import com.whli.autumn.core.web.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>用户公司关系数据模型</p>
 * @author whli
 * @version 1.0.0
 * @since 2019-10-14 20:59
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("ts_user_company")
@ApiModel(description = "用户公司关系")
public class UserCompany extends BaseEntity{
	/**   用户ID */
	@ApiModelProperty(value = "userId")
	private String userId;

	/**   公司ID */
	@ApiModelProperty(value = "companyId")
	private String companyId;

}
