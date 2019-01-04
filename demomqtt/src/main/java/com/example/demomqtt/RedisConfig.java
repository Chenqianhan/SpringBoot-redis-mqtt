package com.example.demomqtt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    /*

     */
    @Autowired
    private RedisConfigBean redisConfigBean;

    /*
    用Lettuce连接Redis

     */
    @Bean
    public LettuceConnectionFactory connectionFactory() {
        LettuceConnectionFactory lf=new LettuceConnectionFactory();
        lf.setDatabase(redisConfigBean.getDatabase());
        lf.setHostName(redisConfigBean.getHost());
        lf.setPassword(redisConfigBean.getPassword());
        lf.setPort(redisConfigBean.getPort());

        return lf;
    }

    @Bean
    public RedisTemplate<String, User> redisTemplate() {
        RedisTemplate<String,User> redisTemplate=new RedisTemplate<>();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setConnectionFactory(connectionFactory());
        return redisTemplate;
    }



    @Bean
    public HashOperations hashOperations() {
        return redisTemplate().opsForHash();
    }
}

