package org.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
@Slf4j
public class RedisConfig {
    @Bean
    public JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        // 最大活跃连接数
        poolConfig.setMaxTotal(8);
        // 最大空闲连接数
        poolConfig.setMaxIdle(8);
        // 最小空闲连接数
        poolConfig.setMinIdle(0);
        // 连接耗尽时是否阻塞
        poolConfig.setBlockWhenExhausted(true);
        // 获取连接的最大等待时间（毫秒）
        poolConfig.setMaxWaitMillis(10000);
        // 获取连接时测试可用性
        poolConfig.setTestOnBorrow(true);
        return poolConfig;
    }
    // 配置 Redis 连接工厂（核心，建立 Redis 连接）
    @Bean
    public RedisConnectionFactory jedisConnectionFactory(JedisPoolConfig jedisPoolConfig) {
        RedisStandaloneConfiguration standaloneConfig = new RedisStandaloneConfiguration();
        // Redis 服务器地址
        standaloneConfig.setHostName("localhost");
        // Redis 端口
        standaloneConfig.setPort(6379);
        // 使用的数据库索引
        standaloneConfig.setDatabase(0);

        JedisClientConfiguration clientConfig = JedisClientConfiguration.builder()
                .usePooling()
                .poolConfig(jedisPoolConfig)
//                .commandTimeout(Duration.ofSeconds(2))
                .build();

        JedisConnectionFactory factory = new JedisConnectionFactory(standaloneConfig, clientConfig);
        return factory;
    }
    @Bean
    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        log.info("开始创建redis模板对象...");

        RedisTemplate<String,Object> redisTemplate = new RedisTemplate<>();
        //设置redis的连接工厂对象
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        //设置redis key的序列化器
        // key 序列化：字符串
        StringRedisSerializer stringSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);

        // value 序列化：JSON
//        Jackson2JsonRedisSerializer<Object> jsonSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        GenericJackson2JsonRedisSerializer jsonSerializer = new GenericJackson2JsonRedisSerializer();
//        ObjectMapper om = new ObjectMapper();
//        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        jsonSerializer.setObjectMapper(om);

        redisTemplate.setValueSerializer(jsonSerializer);
        redisTemplate.setHashValueSerializer(jsonSerializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

//    @Bean
//    public RedissonClient redissonClient() {
//        Config config = new Config();
//        config.useSingleServer()
//                .setAddress("redis://127.0.0.1:6379")
//                .setPassword(null)  // 有密码则设置
//                .setDatabase(0)
//                .setConnectionMinimumIdleSize(10)
//                .setIdleConnectionTimeout(10000)
//                .setConnectTimeout(10000)
//                .setTimeout(3000);
//
//        return Redisson.create(config);
//    }
}
