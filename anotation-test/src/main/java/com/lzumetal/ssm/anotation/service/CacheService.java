package com.lzumetal.ssm.anotation.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * 缓存服务
 */
@Service
public class CacheService {


    @Async
    public void cacheData() {
        try {
            TimeUnit.SECONDS.sleep(5L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("store the result to cache");
    }

}
