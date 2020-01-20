package com.whli.autumn.system.dao;

import com.whli.autumn.core.web.dao.IBaseDao;
import com.whli.autumn.model.system.OperationLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>系统日志数据操作</p>
 * @author whli
 * @version 1.0.0
 * @since 2019-09-01 17:13
 */
@Mapper
public interface IOperationLogDao extends IBaseDao<OperationLog> {

}
