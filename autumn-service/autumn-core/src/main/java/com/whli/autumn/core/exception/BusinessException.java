package com.whli.autumn.core.exception;

/**
 * <p>自定义异常类</p>
 * @author  whli
 * @version 1.0
 * @since  2018/5/29 16:24
 */
public class BusinessException extends RuntimeException{
    /**
     * 错误编码
     */
    private Integer errorCode = -1;

    /**
     * 构造一个基本异常.
     *
     * @param message
     *            信息描述
     */
    public BusinessException(String message)
    {
        super(message);
    }

    /**
     * 构造一个基本异常.
     *
     * @param message
     *            信息描述
     * @param cause
     *            根异常类（可以存入任何异常）
     */
    public BusinessException(String message, Throwable cause)
    {
        super(message, cause);
    }

    /**
     * 构造一个基本异常.
     *
     * @param errorCode
     *            错误编码
     * @param message
     *            信息描述
     */
    public BusinessException(Integer errorCode, String message)
    {
        this(errorCode, message,null);
    }

    /**
     * 构造一个基本异常.
     *
     * @param errorCode
     *            错误编码
     * @param message
     *            信息描述
     */
    public BusinessException(Integer errorCode, String message, Throwable cause)
    {
        this(message,cause);
        this.setErrorCode(errorCode);

    }



    public Integer getErrorCode()
    {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode)
    {
        this.errorCode = errorCode;
    }
}
