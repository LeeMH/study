package me.mhlee.redisexam.domain;

import lombok.AllArgsConstructor;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class RedisSampleService{

    private StringRedisTemplate stringRedisTemplate;

    public String getRedisStringValue(String key) {
        ValueOperations<String, String> stringValueOperations = stringRedisTemplate.opsForValue();
        System.out.println(String.format("key = [%s], value = [%s]", key, stringValueOperations.get(key)));

        return stringValueOperations.get(key);
    }

    public boolean setRedisStringValue(String key, String value) {
        ValueOperations<String, String> stringValueOperations = stringRedisTemplate.opsForValue();
        stringValueOperations.set(key, value);

        return true;
    }
}
