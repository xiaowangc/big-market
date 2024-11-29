package com.chige.infrastructure.redis;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author wangyc
 * @Description Redis缓存实现层
 * @Date 2024/11/29 11:07
 */
@Slf4j
@Service
public class RedisService implements IRedisService {

    @Resource
    private RedissonClient redissonClient;

    @Override
    public <T> void setValue(String key, T value) {

    }

    @Override
    public <T> void setValue(String key, T value, long expire) {

    }

    @Override
    public <T> T get(String key) {
        return null;
    }

    @Override
    public <T> RQueue<T> getQueue(String key) {
        return null;
    }

    @Override
    public <T> RBlockingQueue<T> getBlockQueue(String key) {
        return null;
    }

    @Override
    public <T> RDelayedQueue<T> getDelayQueue(RBlockingQueue<T> blockingQueue) {
        return null;
    }

    @Override
    public long incr(String key) {
        return 0;
    }

    @Override
    public long incrBy(String key, long delta) {
        return 0;
    }

    @Override
    public long decr(String key) {
        return 0;
    }

    @Override
    public long decrBy(String key, long delta) {
        return 0;
    }

    @Override
    public void del(String key) {

    }

    @Override
    public boolean isExists(String key) {
        return false;
    }

    @Override
    public void addToSet(String key, String value) {

    }

    @Override
    public boolean isSetMember(String key, String value) {
        return false;
    }

    @Override
    public void addToList(String key, String value) {

    }

    @Override
    public String getFromList(String key, long index) {
        return null;
    }

    @Override
    public <K, V> RMap<K, V> getMap(String key) {
        return null;
    }

    @Override
    public void addToMap(String key, String field, String value) {

    }

    @Override
    public String getFromMap(String key, String field) {
        return null;
    }

    @Override
    public <K, V> V getFromMap(String key, K field) {
        return null;
    }

    @Override
    public void addToSortedSet(String key, String value) {

    }

    @Override
    public RLock getLock(String key) {
        return null;
    }

    @Override
    public RLock getFairLock(String key) {
        return null;
    }

    @Override
    public RReadWriteLock getReadWriteLock(String key) {
        return null;
    }

    @Override
    public RSemaphore getSemaphore(String key) {
        return null;
    }

    @Override
    public RPermitExpirableSemaphore getPermitExpirableSemaphore(String key) {
        return null;
    }

    @Override
    public RCountDownLatch getCountDownLatch(String key) {
        return null;
    }

    @Override
    public RBloomFilter<String> getBloomFilter(String key) {
        return null;
    }
}
