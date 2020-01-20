package com.whli.autumn.system.controller;

import com.whli.autumn.core.web.controller.BaseController;
import com.whli.autumn.core.web.service.IBaseService;
import com.whli.autumn.model.system.OperationLog;
import com.whli.autumn.system.service.IOperationLogService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>系统日志API</p>
 * @author whli
 * @version 1.0.0
 * @since 2019-09-01 17:13
 */
@RestController
@RequestMapping(value="/system/operationLog")
@Api(description = "系统日志API")
public class OperationLogController extends BaseController<OperationLog>{

	@Autowired
	private IOperationLogService operationLogService;
	
	@Override
    public IBaseService<OperationLog> getService() {
        return operationLogService;
    }
}

