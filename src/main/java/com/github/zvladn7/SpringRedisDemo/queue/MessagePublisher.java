package com.github.zvladn7.SpringRedisDemo.queue;

public interface MessagePublisher {

    void publish(final String message);

}
