package com.sport.peking.poper.config.redis;

import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.io.*;

public class OwnRedisSerializer implements RedisSerializer<Object> {
    @Override
    public byte[] serialize(Object o) throws SerializationException {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(o);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArrayOutputStream.toByteArray();
    }

    @Override
    public Object deserialize(byte[] bytes) throws SerializationException {

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        ObjectInputStream objectInputStream = null;
        Object o = null;
        try {
            objectInputStream = new ObjectInputStream(byteArrayInputStream);
            o = objectInputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return o;
    }
}
