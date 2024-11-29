package com.chige.infrastructure.redis;

import org.redisson.api.*;

/**
 * @Author wangyc
 * @Description 缓存接口服务
 * @Date 2024/11/29 10:41
 */
public interface IRedisService {

    /** set操作
     * 设置指定key
     * @param key key值
     * @param value value值
     */
    <T> void setValue(String key, T value);

    /** set操作
     * 设置指定key
     * @param key key值
     * @param value value值
     * @param expire 过期时间，单位:秒
     */
    <T> void setValue(String key, T value, long expire);

    /** get操作
     * 获取指定key
     */
    <T> T get(String key);

    /**
     * 获取队列
     *
     * @param key 键
     * @param <T> 泛型
     * @return 队列
     */
    <T> RQueue<T> getQueue(String key);

    /**
     * 获取加锁队列
     * @param key 键
     * @param <T> 泛型
     * @return 加锁队列
     */
    <T> RBlockingQueue<T> getBlockQueue(String key);

    /**
     * 延迟队列
     * @param blockingQueue 加锁队列
     * @return 延迟队列
     */
    <T> RDelayedQueue<T> getDelayQueue(RBlockingQueue<T> blockingQueue);

    /**
     * 自增 Key 的值；1、2、3、4
     *
     * @param key 键
     * @return 自增后的值
     */
    long incr(String key);

    /**
     * 指定值，自增 Key 的值；1、2、3、4
     *
     * @param key 键
     * @param delta 自增值
     * @return 自增后的值
     */
    long incrBy(String key, long delta);

    /**
     * 自减 Key 的值；1、2、3、4
     *
     * @param key 键
     * @return 自增后的值
     */
    long decr(String key);

    /**
     * 指定值，自增 Key 的值；1、2、3、4
     *
     * @param key 键
     * @return 自增后的值
     */
    long decrBy(String key, long delta);

    /**
     * 删除指定key
     */
    void del(String key);

    /**
     * 判断指定 key 的值是否存在
     *
     * @param key 键
     * @return true/false
     */
    boolean isExists(String key);

    /**
     * 将指定的值添加到集合中
     *
     * @param key   键
     * @param value 值
     */
    void addToSet(String key, String value);

    /**
     * 判断指定的值是否是集合的成员
     *
     * @param key   键
     * @param value 值
     * @return 如果是集合的成员返回 true，否则返回 false
     */
    boolean isSetMember(String key, String value);

    /**
     * 将指定的值添加到列表中
     *
     * @param key   键
     * @param value 值
     */
    void addToList(String key, String value);


    /**
     * 获取列表中指定索引的值
     *
     * @param key   键
     * @param index 索引
     * @return 值
     */
    String getFromList(String key, long index);

    /**
     * 获取map集合
     */
    <K, V> RMap<K, V> getMap(String key);

    /**
     * 将指定的键值对添加到哈希表中
     *
     * @param key   键
     * @param field 字段
     * @param value 值
     */
    void addToMap(String key, String field, String value);

    /**
     * 获取哈希表中指定字段的值
     * @param key 键
     * @param field field值
     * @return 值
     */
    String getFromMap(String key, String field);

    /**
     * 获取哈希表中指定字段的值
     *
     * @param key   键
     * @param field field值
     * @return 值
     */
    <K, V> V getFromMap(String key, K field);

    /**
     * 将指定的值添加到有序集合中
     *
     * @param key   键
     * @param value 值
     */
    void addToSortedSet(String key, String value);

    /**
     * 获取Redis 锁（可重入）
     */
    RLock getLock(String key);

    /**
     * 获取Redis 锁（公平锁）
     * @param key 健
     * @return 公平锁
     */
    RLock getFairLock(String key);

    /**
     * 获取Redis 锁（读写锁）
     */
    RReadWriteLock getReadWriteLock(String key);

    /**
     * 获取Redis 信号量
     */
    RSemaphore getSemaphore(String key);

    /**
     * 获取 Redis 过期信号量
     * <p>
     * 基于Redis的Redisson的分布式信号量（Semaphore）Java对象RSemaphore采用了与java.util.concurrent.Semaphore相似的接口和用法。
     * 同时还提供了异步（Async）、反射式（Reactive）和RxJava2标准的接口。
     *
     * @param key 键
     * @return RPermitExpirableSemaphore
     */
    RPermitExpirableSemaphore getPermitExpirableSemaphore(String key);

    /**
     * 获取Redis 闭锁
     */
    RCountDownLatch getCountDownLatch(String key);

    /**
     * 布隆过滤器
     */
    RBloomFilter<String> getBloomFilter(String key);

}
