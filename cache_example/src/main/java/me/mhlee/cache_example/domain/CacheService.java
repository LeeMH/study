package me.mhlee.cache_example.domain;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
@Slf4j
public class CacheService {

    @Autowired
    private CacheManager cacheManager;

    @Cacheable(cacheNames = "exampleStore", key = "#key")
    public String getCacheData(final String key) {
        return getHash(key, "");
    }

    @Cacheable(cacheNames = "exampleStore", key = "{ #root.methodName, #key, #userId }")
    public String getCacheData(final String key, final String userId) {
        return getHash(key, userId);
    }

    public String getHash(final String key, final String userId) {
        String plainText = key + userId;
        log.info("no cache [{}]", key + userId);
        return DigestUtils.md5DigestAsHex(plainText.getBytes());
    }
}
