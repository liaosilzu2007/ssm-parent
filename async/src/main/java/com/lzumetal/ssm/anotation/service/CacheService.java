package com.lzumetal.ssm.anotation.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * 缓存服务
 */
@Service
public class CacheService {

    private static final Logger log = LoggerFactory.getLogger(CacheService.class);


    @Async(value = "myexecutor")    //指定执行任务的TaskExecutor
    public void cacheData() {
        try {
            TimeUnit.SECONDS.sleep(3L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.error("success store the result to cache");
    }


    @Async
    public Future<String> cacheDataWithReturn() {
        try {
            TimeUnit.SECONDS.sleep(3L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.error("success store the result to cache");
        return new AsyncResult<>("Async operation success");
    }
}
