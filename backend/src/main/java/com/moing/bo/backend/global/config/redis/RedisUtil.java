package com.moing.bo.backend.global.config.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Repository
public class RedisUtil {

    private RedisTemplate redisTemplate;

    @Value("${jwt.refresh-token-period}")
    private long refreshTokenValidityTime;

    public RedisUtil(final RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void save(String refreshToken, String logInId) {
        //동일한 key 값으로 저장하면 value값 update됨
        redisTemplate.opsForValue().set(logInId, refreshToken, refreshTokenValidityTime, TimeUnit.SECONDS);
    }

    public void deleteById(String logInId) {
        redisTemplate.delete(String.valueOf(logInId));
    }

    public Optional<String> findById(final String logInId) {
        String refreshToken = (String) redisTemplate.opsForValue().get(logInId);
        return Optional.ofNullable(refreshToken);
    }
}
