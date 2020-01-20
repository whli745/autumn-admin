<#assign className = table.className>
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.${moduleName}.service;

import ${superServicePackage}.${superService};
import ${basepackage}.model.${moduleName}.${className};

/**
 * <p>${table.remarks!}业务</p>
 * @author ${author}
 * @version 1.0.0
 * @since ${.now?string["yyyy-MM-dd HH:mm"]}
 */
public interface I${className}Service extends ${superService}<${className}>{
	
}
