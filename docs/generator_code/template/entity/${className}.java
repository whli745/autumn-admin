<#assign className = table.className>
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.model.${moduleName};

import ${superEntityPackage}.${superEntity};

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>${table.remarks!}数据模型</p>
 * @author ${author}
 * @version 1.0.0
 * @since ${.now?string["yyyy-MM-dd HH:mm"]}
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("${table.sqlName}")
@ApiModel(description = "${table.remarks!}")
public class ${className} extends BaseEntity{
<#list table.columns as column>
<#if !column.pk && column.columnNameLower != 'createBy' && column.columnNameLower != 'createDate'
		&& column.columnNameLower != 'updateBy' && column.columnNameLower != 'updateDate'>
	/**   ${column.columnAlias!} */
	@ApiModelProperty(value = "${column.columnAlias!}")
	private ${column.javaType} ${column.columnNameLower};

</#if>
</#list>
}
