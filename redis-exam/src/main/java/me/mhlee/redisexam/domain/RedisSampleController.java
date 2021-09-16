package me.mhlee.redisexam.domain;

import lombok.AllArgsConstructor;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class RedisSampleController {

    private RedisSampleService redisSampleService;

    @GetMapping(value = "/get")
    public String getRedisValue(@RequestParam String key) {
        return redisSampleService.getRedisStringValue(key);
    }

    @GetMapping(value = "/set")
    public String setRedisValue(@RequestParam String key, @RequestParam String value) {
        return String.valueOf(redisSampleService.setRedisStringValue(key, value));
    }

    @GetMapping(value = "/session")
    public String getSession(HttpSession session) {
        return session.getId();
    }
}
