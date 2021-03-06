package com.github.zvladn7.SpringRedisDemo.queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

@Service
public class MessagePublisherImpl implements MessagePublisher {


    private final RedisTemplate<String, Object> redisTemplate;
    private final ChannelTopic topic;


    @Autowired
    public MessagePublisherImpl(
            RedisTemplate<String, Object> redisTemplate,
            ChannelTopic topic
    ) {
        this.redisTemplate = redisTemplate;
        this.topic = topic;
    }

    @Override
    public void publish(String message) {
        redisTemplate.convertAndSend(topic.getTopic(), message);
    }
}
