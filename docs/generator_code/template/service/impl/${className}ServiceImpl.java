<#assign className = table.className>
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.${moduleName}.service.impl;

import ${superMapperPackage}.${superMapper};
import ${superServiceImplPackage}.${superServiceImpl};
import ${basepackage}.${moduleName}.dao.I${className}Dao;
import ${basepackage}.model.${moduleName}.${className};
import ${basepackage}.${moduleName}.service.I${className}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>${table.remarks!}业务实现</p>
 * @author ${author}
 * @version 1.0.0
 * @since ${.now?string["yyyy-MM-dd HH:mm"]}
 */
@Service("${classNameLower}Service")
public class ${className}ServiceImpl extends ${superServiceImpl}<${className}> implements I${className}Service {

	@Autowired
	private I${className}Dao ${classNameLower}Dao;

	@Override
	public ${superMapper}<${className}> getDao() {
		return ${classNameLower}Dao;
	}
}
