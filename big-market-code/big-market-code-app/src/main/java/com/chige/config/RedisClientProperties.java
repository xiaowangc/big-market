package com.chige.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author wangyc
 * @Description Redis配置类
 * @Date 2024/11/28 18:09
 */
@Data
@ConfigurationProperties(prefix = "redis.sdk.config", ignoreInvalidFields = true)
public class RedisClientProperties {

    /** host:ip */
    private String host;
    /** 端口 */
    private int port;
    /** 密码 */
    private String password;
    /** 设置连接池的大小，默认为64 */
    private int poolSize = 64;
    /** 设置连接池的最小空闲连接数，默认为10 */
    private int minIdleSize = 10;
    /** 设置连接的最大空闲时间（单位：毫秒），超过该时间的空闲连接数将被关闭，默认为10000 */
    private int idleTimeout = 10000;
    /** 连接超时时间（单位：毫秒），默认为10000 */
    private int connectTimeout = 10000;
    /** 设置连接重试次数，默认为3 */
    private int retryAttempts = 3;
    /** 设置重连的间隔时间（单位：毫秒），默认为1000 */
    private int retryInterval = 1000;
    /** 设置定期检查连接是否可用的时间间隔（单位：毫秒），默认为0，表示不进行定期检查 */
    private int pingInterval = 0;
    /** 设置是否保持长连接，默认为true */
    private boolean keepAlive = true;

}
