package com.whli.autumn.core.exception.handler;

import com.whli.autumn.core.exception.BusinessException;
import com.whli.autumn.core.share.ResponseBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * 异常处理
 * Created by whli on 2018/1/18.
 */
@RestControllerAdvice
@Slf4j
public class BusinessExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseBean defaultErrorHandler(BusinessException e){
        log.error("com.whli.autumn.core.exception.handler.BusinessExceptionHandler.defaultErrorHandler：",e);
        return ResponseBean.fail(e.getErrorCode(),e.getMessage());
    }


    /**
     * 未发现路径异常
     * @param e
     * @return
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseBean handlerNoFoundException(NoHandlerFoundException e) {
        log.error(e.getMessage(), e);
        return ResponseBean.fail(404, "路径不存在，请检查路径是否正确");
    }

    /**
     * 数据库重复记录异常
     * @param e
     * @return
     */
    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseBean handleDuplicateKeyException(DuplicateKeyException e){
        log.error(e.getMessage(), e);
        return ResponseBean.fail(-10001,"数据库中已存在该记录");
    }

    /**
     * 数据库其它异常
     * @param e
     * @return
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseBean handleDataIntegrityViolationException(DataIntegrityViolationException e){
        String msg = e.getMessage();
        log.error(msg,e);
        if(msg.indexOf("Cannot delete or update a parent row: a foreign key constraint fails") > 0){
            return ResponseBean.fail(-10001,"数据被引用，不能删除！");
        }
        return ResponseBean.fail(-10001,"数据库异常！！");
    }

    @ExceptionHandler(Exception.class)
    public ResponseBean handleException(Exception e){
        log.error(e.getMessage(),e);
        return ResponseBean.fail(-10000,"应用错误，请联系管理员;");
    }
}
