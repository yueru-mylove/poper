package com.sport.peking.poper.config.redis;


import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCachePrefix;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;


/**
 * @author miracle
 */
@Configuration
@EnableCaching
public class RedisConfig {


    @Bean
    public CacheManager cacheManager(RedisTemplate redisTemplate) {
        RedisCacheManager redisCacheManager = new RedisCacheManager(redisTemplate);
        redisCacheManager.setDefaultExpiration(600L);
        redisCacheManager.setUsePrefix(true);
        RedisCachePrefix prefix = String::getBytes;
        redisCacheManager.setCachePrefix(prefix);
        return redisCacheManager;
    }


    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        /* GenericJackson2JsonRedisSerializer相比Jackson2JsonRedisSerializer 调用构造的时候不用指定类型*/
        // GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer();
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);

        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    /**
     * redis key生成策略
     * target: 类
     * method: 方法
     * params: 参数
     * @return KeyGenerator
     *
     * 注意: 该方法只是声明了key的生成策略,还未被使用,需在@Cacheable注解中指定keyGenerator
     *      如: @Cacheable(value = "key", keyGenerator = "cacheKeyGenerator")
     */
    @Bean
    public KeyGenerator cacheKeyGenerator() {
        return (target, method, params) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(target.getClass().getName());
            sb.append(method.getName());
            for (Object obj : params) {
                // 由于参数可能不同, hashCode肯定不一样, 缓存的key也需要不一样
                sb.append(JSON.toJSONString(obj).hashCode());
            }
            return sb.toString();
        };
    }



}



