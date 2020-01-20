<#assign className = table.className>
<#assign classNameLower = className?uncap_first>   
package ${basepackage}.${moduleName}.dao;

import ${superMapperPackage}.${superMapper};
import ${basepackage}.model.${moduleName}.${className};
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>${table.remarks!}数据操作</p>
 * @author ${author}
 * @version 1.0.0
 * @since ${.now?string["yyyy-MM-dd HH:mm"]}
 */
@Mapper
public interface I${className}Dao extends ${superMapper}<${className}>{

}
