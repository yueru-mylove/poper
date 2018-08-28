package com.sport.peking.poper.config.redis;

import com.alibaba.fastjson.JSON;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.util.Assert;

import java.nio.charset.Charset;

/**
 * 避免key类型为object时异常
 */
public class MyStringRedisSerializer implements RedisSerializer<Object> {

    private final Charset charset;
    private final String target = "\"";
    private final String replacement = "";

    public MyStringRedisSerializer() {
        this(Charset.forName("UTF8"));
    }

    public MyStringRedisSerializer(Charset charset) {
        Assert.notNull(charset, "Charset must not be null!");
        this.charset = charset;
    }

    @Override
    public String deserialize(byte[] bytes) {
        return (bytes == null ? null : new String(bytes, charset));
    }

    @Override
    public byte[] serialize(Object object) {
        String string = JSON.toJSONString(object);
        if (string == null) {
            return null;
        }
        string = string.replace(target, replacement);
        return string.getBytes(charset);
    }

}
