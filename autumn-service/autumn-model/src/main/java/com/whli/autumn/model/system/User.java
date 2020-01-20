package com.whli.autumn.model.system;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.whli.autumn.core.web.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * <p>用户数据模型</p>
 * @author whli
 * @version 1.0.0
 * @since 2019-09-01 17:13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("ts_user")
@ApiModel(description = "用户")
public class User extends BaseEntity{

	/**   登录名 */
	@ApiModelProperty(value = "登录名")
	private String loginName;

	/**   密码 */
	@ApiModelProperty(value = "密码")
	private String password;

	/**   工牌号 */
	@ApiModelProperty(value = "工牌号")
	private String tradeNumber;

	/**   姓名 */
	@ApiModelProperty(value = "姓名")
	private String userName;

	/**   邮箱 */
	@ApiModelProperty(value = "邮箱")
	private String email;

	/**   电话 */
	@ApiModelProperty(value = "电话")
	private String phone;

	/**   用户头像 */
	@ApiModelProperty(value = "用户头像")
	private String photo;

	/**   最后登陆IP */
	@ApiModelProperty(value = "最后登陆IP")
	private String loginIp;

	/**   最后登陆时间 */
	@ApiModelProperty(value = "最后登陆时间")
	private Date loginDate;

	/**   superAdmin */
	@ApiModelProperty(value = "超级管理员")
	private String superAdmin;

	/**   是否启用 */
	@ApiModelProperty(value = "是否启用")
	private String enabled;

	/**   备注信息 */
	@ApiModelProperty(value = "备注信息")
	private String remark;

	/**   部门ID */
	@ApiModelProperty(value = "部门ID")
	private String departId;

	/**   第三方登录唯一键 */
	@ApiModelProperty(value = "第三方登录唯一键")
	private String unionId;

	/**   逻辑删除标识 */
	@TableLogic
	@ApiModelProperty(value = "逻辑删除")
	private Integer deleted;

}
