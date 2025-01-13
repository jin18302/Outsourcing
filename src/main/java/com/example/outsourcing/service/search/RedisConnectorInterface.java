package com.example.outsourcing.service.search;

import org.springframework.data.redis.core.ZSetOperations;

public interface RedisConnectorInterface {
    ZSetOperations<String, String> opsForZSet();
    boolean delete(String key);
}
