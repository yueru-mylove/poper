package com.sport.peking.poper.redis;


import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author :CX
 * @Date :Create in 2018/8/15 14:19
 * @Effect : redisDAO封裝
 */
@Component
public class RedisDao {

    private static Logger logger = LoggerFactory.getLogger(RedisDao.class);
    @Resource
    private StringRedisTemplate template;

    @Resource
    private RedisTemplate redisTemplate;

    //大客户信息同步到redis时保存的map的key
    private final String BIG_USER_REDIS_KEY = "CM:CHANNELCUSTOMER";

    /**
     * @参数
     * @返回值
     * @创建人 cx
     * @创建时间
     * @描述 大客户添加成功后存到redis
     */
    public boolean setMap(Map<String , Object> map) {

        try {
            redisTemplate.opsForHash().putAll(BIG_USER_REDIS_KEY
                    , map);
            logger.info("同步大客户信息到redis 成功！userId【" + map.get("funiqueid")+ "】");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("同步大客户信息到redis 失败！userId【" + map.get("funiqueid")+ "】");
        return false;
    }


    public Object getMap(String key) {

        try {
            Object o = redisTemplate.opsForHash().get(BIG_USER_REDIS_KEY, key);
            if (null != o) {
                return o;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("获取大客户信息到失败！");
        return null;
    }


    /**
     * @参数
     * @返回值 存在 = true , 不纯在false
     * @创建人 cx
     * @创建时间
     * @描述 判断是否存在 该key对应的值
     */
    public boolean isNull(String key) {
        return template.hasKey(key);
    }

    /**
     * @参数
     * @返回值
     * @创建人 cx
     * @创建时间
     * @描述 设置值 和 过期时间 单位秒
     */
    public boolean setValue(String key, Object val, long expire) {
        if (!this.isNull(key)) {
            //不存在
            String jsonString = JSON.toJSONString(val);
            template.opsForValue().set(key, jsonString, expire);
            logger.info("***************************成功在缓存中插入：" + key);
            return true;
        } else {
            logger.info("***************************【" + key + "】已经存在缓存");
            return false;
        }
    }


    /**
     * @参数
     * @返回值
     * @创建人 cx
     * @创建时间
     * @描述 删除
     */
    public void del(String key) {
        template.delete(key);
    }

    /**
     * @参数
     * @返回值
     * @创建人 cx
     * @创建时间
     * @描述 插入直接覆盖
     */
    public boolean setValue(String key, Object val) {
        //不存在
        String jsonString = JSON.toJSONString(val);
        // 去掉多余的 “
        String replace = jsonString.replace("\"", "");
        template.opsForValue().set(key, replace);
        logger.info("***************************成功在缓存中插入：" + key);
        return true;
    }

    /**
     * @参数
     * @返回值
     * @创建人 cx
     * @创建时间
     * @描述 获取对应key 的值
     */
    public String getValue(String key) {
        if (!this.isNull(key)) {

            //不存在
            logger.info("***************************【" + key + "】不存在缓存");
            return null;
        } else {
            return template.opsForValue().get(key);//根据key获取缓存中的val
        }
    }


}