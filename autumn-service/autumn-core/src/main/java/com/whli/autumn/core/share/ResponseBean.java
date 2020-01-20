package com.whli.autumn.core.share;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <em>返回结果JavaBean</em>
 * Created by whli on 2018/1/18.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseBean implements Serializable {

    public static final Integer SUCCESS_CODE = 0;
    public static final String SUCCESS_MESSAGE = "操作成功";
    public static final Integer FAIL_CODE = -1;
    public static final String FAIL_MESSAGE = "操作失败";

    private Integer code;
    private String message;
    private Integer count;
    private Object data;

    public static ResponseBean success(){
        return success(SUCCESS_MESSAGE,null);
    }

    public static ResponseBean success(String message){
        return success(message,null);
    }

    public static ResponseBean success(Object data){
        return success(null,data);
    }

    public static ResponseBean success(Integer code,String message){
        return getInstance(code,message,null);
    }

    public static ResponseBean success(String message,Object data){
        return getInstance(SUCCESS_CODE,message,data);
    }

    public static ResponseBean fail(){
        return fail(FAIL_MESSAGE,null);
    }

    public static ResponseBean fail(String message){
        return fail(message,null);
    }

    public static ResponseBean fail(Object data){
        return success(null,data);
    }

    public static ResponseBean fail(Integer code,String message){
        return getInstance(code,message,null);
    }

    public static ResponseBean fail(String message,Object data){
        return getInstance(FAIL_CODE,message,data);
    }

    public static ResponseBean getInstance(Integer code,String message,Object data){
        return getInstance(code,message,data,null);
    }

    public static ResponseBean getInstance(Integer code,String message,Object data,Integer count){
        return new ResponseBean(code,message,count,data);
    }
}
