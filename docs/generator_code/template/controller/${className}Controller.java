<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
package ${basepackage}.${moduleName}.controller;

import ${superControllerPackage}.${superController};
import ${superServicePackage}.${superService};
import ${basepackage}.model.${moduleName}.${className};
import ${basepackage}.${moduleName}.service.I${className}Service;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>${table.remarks!}API</p>
 * @author ${author}
 * @version 1.0.0
 * @since ${.now?string["yyyy-MM-dd HH:mm"]}
 */
@RestController
@RequestMapping(value="/${moduleName}/${classNameLower}")
@Api(description = "${table.remarks!}")
public class ${className}Controller extends ${superController}<${className}>{

	@Autowired
	private I${className}Service ${classNameLower}Service;
	
	@Override
    public ${superService}<${className}> getService() {
        return ${classNameLower}Service;
    }
}

