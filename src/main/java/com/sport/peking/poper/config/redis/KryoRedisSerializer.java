package com.sport.peking.poper.config.redis;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoException;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.io.ByteArrayOutputStream;

/**
 * @author miracle~
 * @param <T>
 */
public class KryoRedisSerializer<T> implements RedisSerializer<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(KryoRedisSerializer.class);
    private static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
    private static final ThreadLocal<Kryo> KEYOS = ThreadLocal.withInitial(Kryo::new);
    private Class<T> clazz;

    public KryoRedisSerializer() {
    }

    public KryoRedisSerializer(Class<T> clazz) {
        super();
        this.clazz = clazz;
    }

    @Override
    public byte[] serialize(T t) throws SerializationException {
        if (null == t) {
            return EMPTY_BYTE_ARRAY;
        }

        Kryo kryo = KEYOS.get();
        kryo.setReferences(false);
        kryo.register(clazz);

        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            Output output = new Output(byteArrayOutputStream);
            kryo.writeClassAndObject(output, t);
            output.flush();
            return byteArrayOutputStream.toByteArray();
        } catch (KryoException e) {
            e.printStackTrace();
            LOGGER.error("序列化异常：", e.getMessage());
        }
        return EMPTY_BYTE_ARRAY;
    }

    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
        if (null == bytes || bytes.length == 0) {
            return null;
        }
        Kryo kryo = KEYOS.get();
        kryo.setReferences(false);
        kryo.register(clazz);

        try {
            Input input = new Input(bytes);
            return (T) kryo.readClassAndObject(input);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("反序列化异常：", e.getMessage());
        }
        return null;
    }
}
