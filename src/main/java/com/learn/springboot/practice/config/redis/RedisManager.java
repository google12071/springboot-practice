package com.learn.springboot.practice.config.redis;

import org.redisson.api.RBitSet;
import org.redisson.api.RScoredSortedSet;

import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;


/**
 * Redis相关
 */
public interface RedisManager {


    /**
     * 设置有效字符串
     *
     * @param key                键
     * @param value              值
     * @param invalidTimeSeconds 有效时间（单位s）
     */
    void setString(String key, String value, long invalidTimeSeconds);

    /**
     * 设置有效字符串
     *
     * @param key         键
     * @param value       值
     * @param invalidTime 有效时间
     * @param timeUnit    时间单位
     */
    void setString(String key, String value, long invalidTime, TimeUnit timeUnit);


    /**
     * 设置字符串
     *
     * @param key   键
     * @param value 值
     */
    void setString(String key, String value);

    /**
     * 获取字符串
     *
     * @param key 键
     */
    String getString(String key);

    /**
     * 设置map数据类型
     *
     * @param key   键
     * @param value 值
     */
    void setMap(String key, Map<String, String> value, Long expireTime);

    /**
     * 获取map数据类型
     */
    Map<String, String> getMap(String key);

    /**
     * 获取具体属性值
     */
    String getMapFieldValue(String key, String field);

    /**
     * 清空map
     *
     * @param key 键
     */
    void delMap(String key);

    /**
     * 清空String
     *
     * @param key 键
     */
    void delString(String key);

    /**
     * 设置有效list
     *
     * @param key         键
     * @param value       值
     * @param invalidTime 有效时间
     * @param timeUnit    时间单位
     */
    <T> void addList(String key, List<T> value, long invalidTime, TimeUnit timeUnit);

    /**
     * 获取list类型数据
     *
     * @param key 键
     * @return 值
     */
    <T> List<T> getList(String key);

    /**
     * 删除list
     *
     * @param key 键
     */
    void delList(String key);

    /**
     * 不设置过期时间的锁
     */
    void lockAround(String key, Runnable runnable);

    /**
     * 不设置过期时间的锁
     */
    <T> T lockAround(String key, Supplier<T> supplier);


    /**
     * 不带返回参数的分布式锁
     *
     * @param retryMs  尝试加锁等待的时间 单位ms
     * @param expireMs 过期释放的时间 单位ms
     * @param runnable 执行接口
     */
    void lockAround(String key, long retryMs, long expireMs, Runnable runnable);

    /**
     * 带返回参数的分布式锁
     *
     * @param retryMs  尝试加锁等待的时间 单位ms
     * @param expireMs 过期释放的时间 单位ms
     * @param supplier 回调接口
     * @param <T>      返回值
     */
    <T> T lockAround(String key, long retryMs, long expireMs, Supplier<T> supplier);

    /**
     * 获取BlockingQueue
     *
     * @param key key
     * @return 返回值
     */
    <T> BlockingQueue<T> getBlockQueue(String key);

    /**
     * 设置BlockingQueue
     *
     * @param key         key
     * @param value       value
     * @param invalidTime 过期时间
     * @param timeUnit    单位
     */
    <T> void setBlockQueue(String key, List<T> value, long invalidTime, TimeUnit timeUnit);

    /**
     * 删除BlockingQueue
     *
     * @param key key
     */
    void delBlockQueue(String key);

    /**
     * 获取AtomicLong并+1
     *
     * @param key         key
     * @param invalidTime 过期时间
     * @param timeUnit    过期时间单位
     * @return 返回值
     */
    long getAndIncrement(String key, long invalidTime, TimeUnit timeUnit);

    /**
     * -1后获取AtomicLong
     *
     * @param key         key
     * @param invalidTime 过期时间
     * @param timeUnit    过期时间单位
     * @return 返回值
     */
    long decrementAndGet(String key, long invalidTime, TimeUnit timeUnit);

    /**
     * 获取AtomicLong并-1
     *
     * @param key         key
     * @param invalidTime 过期时间
     * @param timeUnit    过期时间单位
     * @return 返回值
     */
    long getAndDecrement(String key, long invalidTime, TimeUnit timeUnit);

    /**
     * +1后获取AtomicLong
     *
     * @param key         key
     * @param invalidTime 过期时间
     * @param timeUnit    过期时间单位
     * @return 返回值
     */
    long incrementAndGet(String key, long invalidTime, TimeUnit timeUnit);

    /**
     * 获取AtomicLong
     *
     * @param key key
     * @return 返回值
     */
    long getAtomicLong(String key);

    /**
     * 删除AtomicLong
     *
     * @param key key
     */
    void delAtomicLong(String key);

    void setBitSet(String key, long bitIndex, boolean value, long time, TimeUnit timeUnit);

    RBitSet getBit(String key);

    <T> void setHyperLogLog(String key, T value);

    long getHyperLogLogCount(String key);

    <T> void addScoredSortedSet(String key, T value, long score, long time, TimeUnit timeUnit);

    <T> RScoredSortedSet<T> getScoredSortedSet(String key);

}
