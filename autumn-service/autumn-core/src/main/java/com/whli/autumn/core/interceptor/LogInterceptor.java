package com.whli.autumn.core.interceptor;

import com.baomidou.mybatisplus.core.toolkit.PluginUtils;
import com.whli.autumn.core.util.BeanUtils;
import com.whli.autumn.core.util.DateUtils;
import com.whli.autumn.core.util.WebUtils;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * <p>Mybatis 操作日志插件</p>
 * @author whli
 * @version 1.0.0
 * @since 2019/10/13 10:43
 */
@Intercepts({
        @Signature(type = StatementHandler.class, method = "prepare",
                args = {Connection.class,Integer.class})
})
public class LogInterceptor implements Interceptor {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Object plugin(Object target) {
        return target instanceof StatementHandler ? Plugin.wrap(target, this) : target;
    }

    @Override
    public void setProperties(Properties properties) {

    }

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = (StatementHandler) PluginUtils.realTarget(invocation.getTarget());
        MetaObject metaObject = SystemMetaObject.forObject(statementHandler);
        MappedStatement mappedStatement = (MappedStatement)metaObject.getValue("delegate.mappedStatement");
        BoundSql boundSql = (BoundSql)metaObject.getValue("delegate.boundSql");
        Connection connection = (Connection)invocation.getArgs()[0];
        String sqlId = mappedStatement.getId();

        //增加系统操作日志
        addSysLog(connection,sqlId,boundSql,mappedStatement.getConfiguration());

        return invocation.proceed();
    }

    /**
     * 保存系统操作日志
     * @author whli
     * @param connection
     */
    @Async
    void addSysLog(Connection connection,String sqlId,BoundSql boundSql,Configuration configuration) {
        if (StringUtils.isEmpty(sqlId)){
            return;
        }

        HttpServletRequest request = WebUtils.getRequest();
        if (request == null){
            return;
        }

        String type = "";

        sqlId = sqlId.toLowerCase();
        sqlId = sqlId.substring(sqlId.lastIndexOf(".")+1);

        if (sqlId.startsWith("add") || sqlId.startsWith("insert") || sqlId.startsWith("create")
                || sqlId.startsWith("new") || sqlId.startsWith("save")){
            type = "ADD";
        }else if (sqlId.startsWith("update") || sqlId.startsWith("modify")){
            type = "UPDATE";
        }else if (sqlId.startsWith("delete") || sqlId.startsWith("del")
                || sqlId.startsWith("remove")){
            type = "DELETE";
        }

        if(StringUtils.isEmpty(type)){
            return;
        }

        PreparedStatement pstmt = null;
        try {
            String msg = getParameterObject(boundSql,configuration);
            String sysLogSql = "insert into ts_operation_log(id,opertion_type,table_name,operation_detail,request_uri,ip,create_by,create_date,update_by,update_date) values(?,?,?,?,?,?,?,?,?,?)";
            pstmt = connection.prepareStatement(sysLogSql);
            pstmt.setString(1, BeanUtils.getSnowId());
            pstmt.setString(2,type);
            pstmt.setString(3,boundSql.getSql());
            pstmt.setString(4,msg);
            pstmt.setString(5,request.getRequestURI());
            pstmt.setString(6,WebUtils.getRemoteIP(request));
            pstmt.setString(7,WebUtils.getCurrentUserName());
            pstmt.setTimestamp(8,new Timestamp(System.currentTimeMillis()));
            pstmt.setString(9,WebUtils.getCurrentUserName());
            pstmt.setTimestamp(10,new Timestamp(System.currentTimeMillis()));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            logger.error("addSysLog;bug:{}", e);
        } finally {
            try{
                if (pstmt != null){
                    pstmt.close();
                }
            }catch (Exception e){
                logger.error("addSysLog;bug:{}", e);
            }
        }
    }

    /**
     * 组合参数及值
     * @param boundSql
     * @return
     */
    private String getParameterObject(BoundSql boundSql, Configuration configuration){
        StringBuffer msg = new StringBuffer("{");
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        Object parameterObject = boundSql.getParameterObject();
        if (parameterMappings != null && parameterMappings.size() > 0) {
            TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
            for (int i = 0; i < parameterMappings.size(); i++) {
                ParameterMapping parameterMapping = parameterMappings.get(i);
                if (parameterMapping.getMode() != ParameterMode.OUT) {
                    Object value;
                    String propertyName = parameterMapping.getProperty();
                    if (boundSql.hasAdditionalParameter(propertyName)) { // issue #448 ask first for additional params
                        value = boundSql.getAdditionalParameter(propertyName);
                    } else if (parameterObject == null) {
                        value = null;
                    } else if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
                        value = parameterObject;
                    } else {
                        MetaObject metaObject = configuration.newMetaObject(parameterObject);
                        value = metaObject.getValue(propertyName);
                    }
                    if(value != null){
                        //判断value类型并做相应处理
                        if(value instanceof Date){
                            value = DateUtils.dateToString((Date) value,DateUtils.DEFAULT_YYYY_MM_DD_HH_MM_SS);
                        }else {
                            value = value.toString();
                        }
                    }

                    msg.append(value+", ") ;
                }
            }
        }
        msg.append("}");
        return msg.toString();
    }
}
