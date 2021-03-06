package com.xiaofei.test.cache;

import com.xiaofei.test.common.JedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;

import java.util.Collection;
import java.util.Set;

@Component
@Slf4j
public class RedisCache<K,V> implements Cache<K,V> {
    private final String CACHE_PERFIX = "cache:";

    @Autowired
    private JedisUtil jedisUtil;

    private byte[] getKey(K k){
        if (k instanceof String){
            return (CACHE_PERFIX+k).getBytes();
        }
        return SerializationUtils.serialize(k);
    }

    @Override
    public V get(K k) throws CacheException {
        log.info("从redis中获取权限数据");
        byte[] bytes = jedisUtil.get(getKey(k));
        if (bytes != null)
             return (V)SerializationUtils.deserialize(bytes);
        return null;
    }

    @Override
    public V put(K k, V v) throws CacheException {
        log.info("将权限数据放入redis中");
        byte[] key = getKey(k);
        byte[] value = SerializationUtils.serialize(v);
        jedisUtil.set(key, value);
        jedisUtil.expire(key,600);
        return v;
    }

    @Override
    public V remove(K k) throws CacheException {
        log.info("移除权限数据");
        byte[] key = getKey(k);
        byte[] value = jedisUtil.get(key);
        jedisUtil.del(key);
        if (value != null){
            return (V)SerializationUtils.deserialize(value);
        }
        return null;
    }

    @Override
    public void clear() throws CacheException {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Set<K> keys() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }

}
