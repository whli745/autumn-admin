package com.whli.autumn.system.service.impl;

import com.whli.autumn.core.web.dao.IBaseDao;
import com.whli.autumn.core.web.service.impl.BaseServiceImpl;
import com.whli.autumn.system.dao.IOperationLogDao;
import com.whli.autumn.model.system.OperationLog;
import com.whli.autumn.system.service.IOperationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>系统日志业务实现</p>
 * @author whli
 * @version 1.0.0
 * @since 2019-09-01 17:13
 */
@Service("operationLogService")
public class OperationLogServiceImpl extends BaseServiceImpl<OperationLog> implements IOperationLogService {

	@Autowired
	private IOperationLogDao operationLogDao;

	@Override
	public IBaseDao<OperationLog> getDao() {
		return operationLogDao;
	}
}
