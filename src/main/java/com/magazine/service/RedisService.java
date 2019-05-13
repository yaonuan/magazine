package com.magazine.service;

import org.springframework.data.redis.core.ZSetOperations;

import java.util.List;
import java.util.Set;

public interface RedisService {

    /**
     * 写入缓存
     *
     * @param key
     * @param offset 位 8Bit=1Byte
     * @param isShow
     * @return
     */
    boolean setBit(String key, long offset, boolean isShow);

    /**
     * 写入缓存
     *
     * @param key
     * @param offset
     * @return
     */
    boolean getBit(String key, long offset);

    /**
     * 写入缓存
     *
     * @param key
     * @param value
     * @return
     */
    boolean set(String key, Object value);

    /**
     * 写入缓存设置时效时间
     *
     * @param key
     * @param value
     * @param expireTime
     * @return
     */
    boolean set(String key, Object value, Long expireTime);

    /**
     * 批量删除对应的value
     *
     * @param keys
     */
    void remove(String... keys);

    /**
     * 删除对应的value
     *
     * @param key
     */
    void remove(String key);

    /**
     * 读取缓存
     *
     * @param key
     * @return
     */
    Object genValue(String key);

    /**
     * 读取缓存
     *
     * @param key
     * @return
     */
    Object get(String key);

    /**
     * 哈希添加
     *
     * @param key
     * @param hashKey
     * @param value
     */
    void hmSet(String key, Object hashKey, Object value);

    /**
     * 哈希获取数据
     *
     * @param key
     * @param hashKey
     * @return
     */
    Object hmGet(String key, Object hashKey);

    /**
     * 列表添加
     *
     * @param k
     * @param v
     */
    void lPush(String k, Object v);

    /**
     * 列表获取
     *
     * @param k
     * @param l
     * @param l1
     * @return
     */
    List<Object> lRange(String k, long l, long l1);

    /**
     * 集合添加
     *
     * @param key
     * @param value
     */
    void add(String key, Object value);

    /**
     * 集合获取
     *
     * @param key
     * @return
     */
    Set<Object> setMembers(String key);

    /**
     * 有序集合添加
     *
     * @param key
     * @param value
     * @param scoure
     */
    void zAdd(String key, Object value, double scoure);

    /**
     * 有序集合获取
     *
     * @param key
     * @param scoure
     * @param scoure1
     * @return
     */
    Set<Object> rangeByScore(String key, double scoure, double scoure1);

    //第一次加载的时候将数据加载到redis中
    void saveDataToRedis(String name);

    //第一次加载的时候将数据加载到redis中
    boolean getDataToRedis(String name);

    /**
     * 有序集合获取排名
     *
     * @param key   集合名称
     * @param value 值
     * @return
     */
    Long zRank(String key, Object value);

    /**
     * 有序集合获取排名
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    Set<ZSetOperations.TypedTuple<Object>> zRankWithScore(String key, long start, long end);

    /**
     * 有序集合添加
     *
     * @param key
     * @param value
     * @return
     */
    Double zSetScore(String key, Object value);

    /**
     * 有序集合添加分数
     *
     * @param key
     * @param value
     * @param scoure
     */
    void incrementScore(String key, Object value, double scoure);

    /**
     * 有序集合获取排名
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    Set<ZSetOperations.TypedTuple<Object>> reverseZRankWithScore(String key, long start, long end);

    /**
     * 有序集合获取排名
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    Set<ZSetOperations.TypedTuple<Object>> reverseZRankWithRank(String key, long start, long end);
}
