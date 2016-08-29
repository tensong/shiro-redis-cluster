package com.michaelliuyang.shiro.demo;

import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.JedisCluster;

/**
 * jedis manager
 *
 * @author michael
 */
public class JedisManager {

//    @Autowired
    private JedisCluster jedisCluster;

    public byte[] getValueByKey(int dbIndex, byte[] key) throws Exception {
        byte[] result = null;
        try {
            result =  jedisCluster.get(key);
        } catch (Exception e) {
            throw e;
        }
        return result;
    }

    public void deleteByKey(int dbIndex, byte[] key) throws Exception {
        try {
            jedisCluster.del(key);
        } catch (Exception e) {
            throw e;
        }
    }

    public void saveValueByKey(int dbIndex, byte[] key, byte[] value, int expireTime)
            throws Exception {
        try {
            jedisCluster.set(key, value);
            if (expireTime > 0)
                jedisCluster.expire(key, expireTime);
        } catch (Exception e) {
            throw e;
        }
    }

    public JedisCluster getJedisCluster() {
        return jedisCluster;
    }

    public void setJedisCluster(JedisCluster jedisCluster) {
        this.jedisCluster = jedisCluster;
    }
}
