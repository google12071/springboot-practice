package com.learn.springboot.practice.config.redis;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.*;
import org.redisson.client.RedisException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * <p>Redisson实现redis</p>
 *
 * @author maven-archetype
 * @since 2020-11-21
 */
@Slf4j
public class RedissonRedisManager implements RedisManager {

    public static final String REDIS_APP_PREFIX = "spring-boot-practise";

    private final RedissonClient redissonClient;

    public RedissonRedisManager(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    @Override
    public void setString(String key, String value, long invalidTimeSeconds) {
        setString(key, value, invalidTimeSeconds, TimeUnit.SECONDS);
    }

    @Override
    public void setString(String key, String value, long invalidTime, TimeUnit timeUnit) {
        String redisKey = wrapPrefix(key);
        try {
            RBucket<String> bucket = redissonClient.getBucket(redisKey);
            bucket.set(value, invalidTime, timeUnit);
        } catch (Exception e) {
            log.error("REDIS_ERROR_SET," + redisKey + ":" + value + ",invalidTimeSeconds=" + invalidTime + "s", e);
            throw new RedisException("REDIS_ERROR_SET");
        }
    }

    @Override
    public void setString(String key, String value) {
        String redisKey = wrapPrefix(key);
        try {
            redissonClient.getBucket(redisKey).set(value);
        } catch (Exception e) {
            log.error("REDIS_ERROR_SET," + redisKey + ":" + value + "s", e);
            throw new RedisException("REDIS_ERROR_SET");
        }
    }

    @Override
    public String getString(String key) {
        String redisKey = wrapPrefix(key);
        try {
            RBucket<String> val = redissonClient.getBucket(redisKey);
            return val == null ? null : val.get();
        } catch (Exception e) {
            log.error("REDIS_ERROR_GET," + redisKey, e);
            throw new RedisException("REDIS_ERROR_GET");
        }
    }

    @Override
    public void setMap(String key, Map<String, String> value, Long expireTime) {
        String redisKey = wrapPrefix(key);
        try {
            redissonClient.getMap(redisKey).expire(expireTime, TimeUnit.SECONDS);
            redissonClient.getMap(redisKey).putAll(value);
        } catch (Exception e) {
            log.error("REDIS_ERROR_SET," + redisKey + ":" + key, e);
            throw new RedisException("REDIS_ERROR_SET");
        }
    }

    @Override
    public Map<String, String> getMap(String key) {
        String redisKey = wrapPrefix(key);
        try {
            Map<String, String> result = new HashMap<>();
            Map<Object, Object> value = redissonClient.getMap(redisKey).readAllMap();
            for (Map.Entry<Object, Object> entry : value.entrySet()) {
                result.put(String.valueOf(entry.getKey()), String.valueOf(entry.getValue()));
            }
            return result;
        } catch (Exception e) {
            log.error("REDIS_ERROR_GET," + redisKey, e);
            throw new RedisException("REDIS_ERROR_GET");
        }
    }

    @Override
    public String getMapFieldValue(String key, String field) {
        String redisKey = wrapPrefix(key);
        try {
            Object object = redissonClient.getMap(redisKey).get(field);
            return object == null ? null : object + "";
        } catch (Exception e) {
            log.error("REDIS_ERROR_GET_MAP," + redisKey + ",field=" + field, e);
            throw new RedisException("REDIS_ERROR_GET_MAP");
        }
    }

    @Override
    public void delMap(String key) {
        String redisKey = wrapPrefix(key);
        try {
            redissonClient.getMap(redisKey).delete();
        } catch (Exception e) {
            log.error("REDIS_ERROR_DEL_KEY," + redisKey, e);
            throw new RedisException("REDIS_ERROR_DEL_KEY");
        }
    }

    @Override
    public void delString(String key) {
        if (StringUtils.isEmpty(key)) {
            return;
        }
        String redisKey = wrapPrefix(key);
        try {
            RBucket<Object> bucket = redissonClient.getBucket(redisKey);
            bucket.delete();
        } catch (Exception e) {
            log.error("REDIS_ERROR_DEL_KEY," + redisKey, e);
            throw new RedisException("REDIS_ERROR_DEL_KEY");
        }
    }

    @Override
    public <T> void addList(String key, List<T> value, long invalidTime, TimeUnit timeUnit) {
        String redisKey = wrapPrefix(key);
        try {
            RList<T> list = redissonClient.getList(redisKey);
            if (!list.isExists()) {
                list.addAll(value);
                list.expire(invalidTime, timeUnit);
            } else {
                list.addAll(value);
            }
        } catch (Exception e) {
            log.error("REDIS_ERROR_SET," + redisKey + ":" + value + ",invalidTimeSeconds=" + invalidTime + "s", e);
            throw new RedisException("REDIS_ERROR_SET");
        }

    }

    @Override
    public <T> List<T> getList(String key) {
        String redisKey = wrapPrefix(key);
        try {
            return redissonClient.getList(redisKey);
        } catch (Exception e) {
            log.error("REDIS_ERROR_GET," + redisKey, e);
            throw new RedisException("REDIS_ERROR_GET");
        }
    }

    @Override
    public void delList(String key) {
        String redisKey = wrapPrefix(key);
        try {
            redissonClient.getList(redisKey).delete();
        } catch (Exception e) {
            log.error("REDIS_ERROR_DEL_KEY," + redisKey, e);
            throw new RedisException("REDIS_ERROR_DEL_KEY");
        }
    }

    @Override
    public void lockAround(String key, long retryMs, long expireMs, Runnable runnable) {
        String redisKey = wrapPrefix(key);
        RLock lock = redissonClient.getLock(redisKey);
        boolean res = false;
        try {
            res = lock.tryLock(retryMs, expireMs, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        if (!res) {
            log.warn("key:{} try lock timeout {}", redisKey, retryMs);
            throw new RedisException(key + " try lock timeout " + retryMs + "ms");
        }
        try {
            runnable.run();
        } finally {
            if (lock.isLocked()) {
                try {
                    lock.unlock();
                } catch (Exception e) {
                }
            }
        }
    }

    @Override
    public <T> T lockAround(String key, long retryMs, long expireMs, Supplier<T> supplier) {
        String redisKey = wrapPrefix(key);
        RLock lock = redissonClient.getLock(redisKey);
        boolean res = false;
        try {
            res = lock.tryLock(retryMs, expireMs, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        if (!res) {
            log.warn("key:{} try lock timeout {}", redisKey, retryMs);
            throw new RedisException(key + " try lock timeout " + retryMs + "ms");
        }
        try {
            return supplier.get();
        } finally {
            if (lock.isLocked()) {
                try {
                    lock.unlock();
                } catch (Exception e) {
                }
            }
        }
    }


    @Override
    public void lockAround(String key, Runnable runnable) {
        String redisKey = wrapPrefix(key);
        RLock lock = redissonClient.getLock(redisKey);
        try {
            lock.lock();
            runnable.run();
        } finally {
            if (lock.isLocked()) {
                try {
                    lock.unlock();
                } catch (Exception e) {
                }
            }
        }
    }

    @Override
    public <T> T lockAround(String key, Supplier<T> supplier) {
        String redisKey = wrapPrefix(key);
        RLock lock = redissonClient.getLock(redisKey);
        try {
            lock.lock();
            return supplier.get();
        } finally {
            if (lock.isLocked()) {
                try {
                    lock.unlock();
                } catch (Exception e) {
                    // ignore
                }
            }
        }
    }

    @Override
    public <T> BlockingQueue<T> getBlockQueue(String key) {
        String redisKey = wrapPrefix(key);
        try {
            return redissonClient.getBlockingQueue(redisKey);
        } catch (Exception e) {
            log.error("REDIS_ERROR_GET_BLOCKING_QUEUE," + redisKey, e);
            throw new RedisException("REDIS_ERROR_GET_BLOCKING_QUEUE");
        }
    }

    @Override
    public <T> void setBlockQueue(String key, List<T> value, long invalidTime, TimeUnit timeUnit) {
        String redisKey = wrapPrefix(key);
        try {
            if (!redissonClient.getBlockingQueue(redisKey).isExists()) {
                for (T t : value) {
                    redissonClient.getBlockingQueue(redisKey).put(t);
                }
                redissonClient.getBlockingQueue(redisKey).expire(invalidTime, timeUnit);
            } else {
                for (T t : value) {
                    redissonClient.getBlockingQueue(redisKey).put(t);
                }
            }
        } catch (InterruptedException e) {
            log.error("REDIS_ERROR_SET_BLOCKING_QUEUE," + redisKey, e);
            throw new RedisException("REDIS_ERROR_SET_BLOCKING_QUEUE");
        }
    }

    @Override
    public void delBlockQueue(String key) {
        String redisKey = wrapPrefix(key);
        try {
            redissonClient.getBlockingQueue(redisKey).delete();
        } catch (Exception e) {
            log.error("REDIS_ERROR_DEL_BLOCKING_QUEUE," + redisKey, e);
            throw new RedisException("REDIS_ERROR_DEL_BLOCKING_QUEUE");
        }
    }

    @Override
    public long getAndIncrement(String key, long invalidTime, TimeUnit timeUnit) {
        String redisKey = wrapPrefix(key);
        try {
            if (!redissonClient.getAtomicLong(redisKey).isExists()) {
                long value = redissonClient.getAtomicLong(redisKey).getAndIncrement();
                redissonClient.getAtomicLong(redisKey).expire(invalidTime, timeUnit);
                return value;
            } else {
                return redissonClient.getAtomicLong(redisKey).getAndIncrement();
            }

        } catch (Exception e) {
            log.error("REDIS_ERROR_GET_AND_INCREMENT_ATOMIC_LONG," + redisKey, e);
            throw new RedisException("REDIS_ERROR_GET_AND_INCREMENT_ATOMIC_LONG");
        }
    }

    @Override
    public long incrementAndGet(String key, long invalidTime, TimeUnit timeUnit) {
        String redisKey = wrapPrefix(key);
        try {
            if (!redissonClient.getAtomicLong(redisKey).isExists()) {
                long value = redissonClient.getAtomicLong(redisKey).incrementAndGet();
                redissonClient.getAtomicLong(redisKey).expire(invalidTime, timeUnit);
                return value;
            } else {
                return redissonClient.getAtomicLong(redisKey).incrementAndGet();
            }
        } catch (Exception e) {
            log.error("REDIS_ERROR_INCREMENT_AND_GET_ATOMIC_LONG," + redisKey, e);
            throw new RedisException("REDIS_ERROR_INCREMENT_AND_GET_ATOMIC_LONG");
        }
    }

    @Override
    public long getAtomicLong(String key) {
        String redisKey = wrapPrefix(key);
        try {
            return redissonClient.getAtomicLong(redisKey).get();
        } catch (Exception e) {
            log.error("REDIS_ERROR_GET_ATOMIC_LONG," + redisKey, e);
            throw new RedisException("REDIS_ERROR_GET_ATOMIC_LONG");
        }
    }

    @Override
    public void delAtomicLong(String key) {
        String redisKey = wrapPrefix(key);
        try {
            redissonClient.getAtomicLong(redisKey).delete();
        } catch (Exception e) {
            log.error("REDIS_ERROR_DEL_ATOMIC_LONG," + redisKey, e);
            throw new RedisException("REDIS_ERROR_DEL_ATOMIC_LONG");
        }
    }

    @Override
    public void setBitSet(String key, long bitIndex, boolean value, long time, TimeUnit timeUnit) {
        String redisKey = wrapPrefix(key);
        try {
            if (!redissonClient.getBitSet(redisKey).isExists()) {
                redissonClient.getBitSet(redisKey).set(bitIndex, value);
                redissonClient.getBitSet(redisKey).expire(time, timeUnit);
            } else {
                redissonClient.getBitSet(redisKey).set(bitIndex, value);
            }
        } catch (Exception e) {
            log.error("REDIS_ERROR_SET_BIT_SET," + redisKey, e);
            throw new RedisException("REDIS_ERROR_SET_BIT_SET");
        }
    }

    @Override
    public <T> void addScoredSortedSet(String key, T value, long score, long time, TimeUnit timeUnit) {
        String redisKey = wrapPrefix(key);
        try {
            RScoredSortedSet<T> scoredSortedSet = redissonClient.getScoredSortedSet(redisKey);
            if (!scoredSortedSet.isExists()) {
                scoredSortedSet.add(score, value);
                scoredSortedSet.expire(time, timeUnit);
            } else {
                scoredSortedSet.add(score, value);

            }
        } catch (Exception e) {
            log.error("REDIS_ERROR_SET_SCORED_SORTED_SET," + redisKey, e);
            throw new RedisException("REDIS_ERROR_SET_SCORED_SORTED_SET");
        }
    }

    @Override
    public <T> RScoredSortedSet<T> getScoredSortedSet(String key) {
        String redisKey = wrapPrefix(key);
        try {
            return redissonClient.getScoredSortedSet(redisKey);
        } catch (Exception e) {
            log.error("REDIS_ERROR_GET_SCORED_SORTED_SET," + redisKey, e);
            throw new RedisException("REDIS_ERROR_GET_SCORED_SORTED_SET");
        }
    }

    /**
     * 设置过期时间
     *
     * @param key      key
     * @param time     时间值
     * @param timeUnit 时间单位
     */
    public void setBitSetExpire(String key, long time, TimeUnit timeUnit) {
        String redisKey = wrapPrefix(key);
        try {
            if (!redissonClient.getBitSet(redisKey).isExists()) {
                redissonClient.getBitSet(redisKey).expire(time, timeUnit);
            }
        } catch (Exception e) {
            log.error("REDIS_ERROR_SET_BIT_SET," + redisKey, e);
            throw new RedisException("REDIS_ERROR_SET_BIT_SET");
        }
    }

    @Override
    public RBitSet getBit(String key) {
        String redisKey = wrapPrefix(key);
        try {
            return redissonClient.getBitSet(redisKey);
        } catch (Exception e) {
            log.error("REDIS_ERROR_GET_BIT_SET," + redisKey, e);
            throw new RedisException("REDIS_ERROR_GET_BIT_SET");
        }
    }

    @Override
    public <T> void setHyperLogLog(String key, T value) {
        String redisKey = wrapPrefix(key);
        try {
            redissonClient.getHyperLogLog(redisKey).add(value);
        } catch (Exception e) {
            log.error("REDIS_ERROR_SET_HLL," + redisKey, e);
            throw new RedisException("REDIS_ERROR_SET_HLL");
        }
    }

    @Override
    public long getHyperLogLogCount(String key) {
        String redisKey = wrapPrefix(key);
        try {
            return redissonClient.getHyperLogLog(redisKey).count();
        } catch (Exception e) {
            log.error("REDIS_ERROR_GET_HLL_COUNT," + redisKey, e);
            throw new RedisException("REDIS_ERROR_GET_HLL_COUNT");
        }
    }


    /**
     * 拼接前缀
     */
    private String wrapPrefix(String key) {
        return REDIS_APP_PREFIX + key;
    }
}
