package com.whli.autumn.core.util;

import java.util.UUID;

/**
 * <em>JavaBean工具类</em>
 * @author whli
 * @since 2018/5/5 9:35
 */
public class BeanUtils {

    private BeanUtils(){}

    /**
     * <em>获取UUID</em>
     * @author whli
     * @since 2018/5/5
     * @return java.lang.String
     */
    public static String getUUID(){
        String uuid =UUID.randomUUID().toString();
        return uuid.replace("-","");
    }

    /**
     * 获取雪花ID
     * @return java.lang.String
     */
    public static String getSnowId(){
        SnowflakeUtils snow = SnowflakeUtils.getInstance(3,1);
        return Long.valueOf(snow.nextId()).toString();
    }

    /**
     * <em>判断对象是否为Null</em>
     * @author whli
     * @since 2018/5/5
     * @param z Object
     * @return boolean
     */
    public static boolean isNull(Object z){
        return z == null;
    }

    /**
     * <em>判断对象是否不为Null</em>
     * @author whli
     * @since 2018/5/5
     * @param z Object
     * @return boolean
     */
    public static boolean isNotNull(Object z){
        return !isNull(z);
    }
}
