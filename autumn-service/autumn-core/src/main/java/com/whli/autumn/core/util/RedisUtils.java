package com.whli.autumn.core.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * <p>Spring Redis工具类</p>
 * @author whli
 * @version 1.0.0
 * @since 2019/9/9 16:35
 */
@Component
public class RedisUtils {

    private static StringRedisTemplate redisTemplate;

    @Autowired
    public void setRedisTemplate(StringRedisTemplate redisTemplate) {
        RedisUtils.redisTemplate = redisTemplate;
    }


    public static boolean set(String key, String value){
        return redisTemplate.opsForValue().setIfPresent(key,value);
    }

    public static boolean set(String key, String value, long timeout, TimeUnit unit){
        return redisTemplate.opsForValue().setIfPresent(key,value,timeout,unit);
    }

    public static boolean multiSet(Map<String,String> params){
        redisTemplate.opsForValue().multiSet(params);
        return true;
    }

    public static Object get(String key){
        return redisTemplate.opsForValue().get(key);
    }

    public static List<String> multiGet(List<String> keys){
        return redisTemplate.opsForValue().multiGet(keys);
    }

    public static boolean multiDelete(List<String> keys){
        Long result = redisTemplate.opsForValue().getOperations().delete(keys);
        return result != null && result.intValue() == 1;
    }


    //------------------------------------------------------HashSet模式--------------------------------------------------
    public static boolean hSet(String key, String field, String value){
        redisTemplate.opsForHash().put(key, field, value);
        return true;
    }

    public static boolean hSet(String key, String field, String value, long timeout, TimeUnit unit){
        boolean flag = hSet(key,field,value);
        redisTemplate.expire(key,timeout,unit);
        return flag;
    }

    public static boolean multiHSet(String key, Map<String,String> value){
        redisTemplate.opsForHash().putAll(key,value);
        return true;
    }

    public static boolean multiHSet(String key, Map<String,String> values, long timeout, TimeUnit unit){
        multiHSet(key,values);
        redisTemplate.expire(key,timeout,unit);
        return true;
    }

    public static Object hGet(String key,Object field){
       return redisTemplate.opsForHash().get(key,field);
    }

    public static List<Object> multiHGet(String key,List<Object> fields){
        return redisTemplate.opsForHash().multiGet(key,fields);
    }

    public static boolean hDelete(String token,Object... field){
        Long flag = redisTemplate.opsForHash().delete(token, field);
        if (1 == flag.intValue()){
            return true;
        }
        return false;
    }

    //------------------------------------------------------Global--------------------------------------------------
    public static boolean exists(String key){
        return redisTemplate.hasKey(key);
    }

    public static boolean delete(String key){
        return redisTemplate.delete(key);
    }
}
