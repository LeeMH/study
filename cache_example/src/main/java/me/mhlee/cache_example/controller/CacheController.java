package me.mhlee.cache_example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.AllArgsConstructor;
import me.mhlee.cache_example.domain.CacheService;



@AllArgsConstructor
@RestController
public class CacheController {

    private CacheService service;

    @GetMapping(value = "/cache")
    public String getCache(String key) {
        return service.getCacheData(key);
    }

    @GetMapping(value = "/cache2")
    public String getCache(String key, String userId) {
        return service.getCacheData(key, userId);
    }
}
