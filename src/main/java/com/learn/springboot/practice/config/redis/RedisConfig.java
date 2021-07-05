package com.learn.springboot.practice.config.redis;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learn.springboot.practice.mq.MessagePublisher;
import com.learn.springboot.practice.mq.MessageSubscriber;
import com.learn.springboot.practice.mq.impl.MessagePublisherImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.ArrayList;

/**
 * @ClassName RedisConfig
 * @Description: Redis序列化配置
 * @Author lfq
 * @Date 2020/4/17
 **/
@Configuration
public class RedisConfig {
    @Bean(name = "jackson2Template")
    public RedisTemplate<String, Object> getTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        Jackson2JsonRedisSerializer<Object> jacksonSerializable = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jacksonSerializable.setObjectMapper(om);

        StringRedisSerializer stringSerial = new StringRedisSerializer();
        template.setKeySerializer(stringSerial);
        template.setValueSerializer(jacksonSerializable);
        template.setHashKeySerializer(stringSerial);
        template.setHashValueSerializer(jacksonSerializable);
        template.afterPropertiesSet();
        return template;
    }

    @Bean(name = "genericJackson2Template")
    public RedisTemplate<String, Object> getRedisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory);
        GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer();
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();

        redisTemplate.setDefaultSerializer(genericJackson2JsonRedisSerializer);
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        redisTemplate.setHashValueSerializer(genericJackson2JsonRedisSerializer);
        return redisTemplate;
    }


    @Bean
    RedisMessageListenerContainer redisContainer(RedisConnectionFactory connectionFactory) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(messageListenerAdapter1(), topic1());
        container.addMessageListener(messageListenerAdapter1(), topic2());
        container.addMessageListener(messageListenerAdapter2(), topic2());
        return container;
    }

    @Bean
    MessageListenerAdapter messageListenerAdapter1() {
        return new MessageListenerAdapter(messageListener1());
    }

    @Bean
    public MessageListener messageListener1() {
        return new MessageSubscriber(new ArrayList<>());
    }

    @Bean
    MessageListenerAdapter messageListenerAdapter2() {
        return new MessageListenerAdapter(messageListener2());
    }

    @Bean
    public MessageListener messageListener2() {
        return new MessageSubscriber(new ArrayList<>());
    }


    @Bean
    MessagePublisher redisPublisherForTopic1(RedisConnectionFactory connectionFactory) {
        return new MessagePublisherImpl(getTemplate(connectionFactory), topic1());
    }

    @Bean
    MessagePublisher redisPublisherForTopic2(RedisConnectionFactory connectionFactory) {
        return new MessagePublisherImpl(getRedisTemplate(connectionFactory), topic2());
    }

    @Bean
    ChannelTopic topic1() {
        return new ChannelTopic("topic1");
    }

    @Bean
    ChannelTopic topic2() {
        return new ChannelTopic("topic2");
    }
}
