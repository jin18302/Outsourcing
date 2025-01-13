package com.example.outsourcing.repository.popSearch;

import com.example.outsourcing.service.search.RedisConnectorInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class RedisConnector implements RedisConnectorInterface {
    private final RedisTemplate<String, String> redisTemplate;

    @Override
    public ZSetOperations<String, String> opsForZSet() {
        return redisTemplate.opsForZSet();
    }

    @Override
    public boolean delete(String key) {
        return redisTemplate.delete(key);
    }
}
