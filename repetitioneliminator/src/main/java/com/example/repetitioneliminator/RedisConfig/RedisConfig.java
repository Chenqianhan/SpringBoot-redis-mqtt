package com.example.repetitioneliminator.RedisConfig;

import com.example.repetitioneliminator.Picture.Picture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
//import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;


@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

    @Autowired
    private RedisConfigBean redisConfigBean;

    /*
    哨兵，待完成。待哨兵配置好加入。然后再测试是否在连接单一redis服务器的情况下，如果master更替，能否自动连上新master。
     */
    /*
    @Bean
    public RedisSentinelConfiguration redisSentinelConfiguration() {
        RedisSentinelConfiguration sentinel=new RedisSentinelConfiguration();
        String[] host=redisConfigBean.getRedisNodes().split(",");
    }
*/
    @Bean
    public LettuceConnectionFactory connectionFactory() {
        LettuceConnectionFactory factory=new LettuceConnectionFactory();
        //lf.setDatabase(redisConfigBean.getDatabase());
        factory.setHostName(redisConfigBean.getHost());
        //lf.setPassword(redisConfigBean.getPassword());
        //lf.setPort(redisConfigBean.getPort());
        factory.setTimeout(redisConfigBean.getTimeout());
        return factory;
    }
    /*
    用这个连接方法会报错。
    @Bean
    public JedisConnectionFactory jedisConnectionFactory(){
        //JedisConnectionFactory factory=new JedisConnectionFactory(redisSentinelConfiguration());
        JedisConnectionFactory factory=new JedisConnectionFactory();
        factory.setHostName(redisConfigBean.getHost());
        //factory.setPort(redisConfigBean.getPort());
        factory.setTimeout(redisConfigBean.getTimeout());
        //factory.setPassword(redisConfigBean.getPassword());
        //factory.setDatabase(redisConfigBean.getDatabase());
        return factory;
    }
*/
    /*
    配置redisTemplate
    设置添加序列化器
    key使用String序列化器
    value使用Json序列话器
    还有一种简单的设置方式，改变defaultSerializer对象的实现
     */
    @Bean
    public RedisTemplate<String, Picture> redisTemplate(){
        RedisTemplate<String, Picture> template=new RedisTemplate<>();
        //设置事务控制
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        template.setEnableTransactionSupport(true);
        template.setKeySerializer(stringRedisSerializer);
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.setHashKeySerializer(stringRedisSerializer);

        template.setConnectionFactory(connectionFactory());
        template.afterPropertiesSet();
        return template;
    }

/*
原代码
    @Bean
    public RedisTemplate<String, Picture> redisTemplate() {
        RedisTemplate<String,Picture> redisTemplate=new RedisTemplate<>();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setConnectionFactory(connectionFactory());
        return redisTemplate;
    }
*/

/*
设置RedisCacheManager
使用cache注解redis缓存
rediscachemanager问题很大，解决不了，暂时用原方案


    @Override
    @Bean
    public RedisCacheManager cacheManager() {
        RedisCacheManager redisCacheManager=new RedisCacheManager(redisTemplate());
        return redisCacheManager;
    }

    @Override
    public KeyGenerator keyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object o, Method method, Object... objects) {
                StringBuilder sb = new StringBuilder();
                sb.append(o.getClass().getName()).append(".");
                sb.append(method.getName()).append(".");
                for (Object obj : objects) {
                    sb.append(obj.toString());
                }
                System.out.println("keyGenerator=" + sb.toString());
                return sb.toString();
            }
        };
    }


*/

    @Bean
    public HashOperations hashOperations() {
        return redisTemplate().opsForHash();
    }
}