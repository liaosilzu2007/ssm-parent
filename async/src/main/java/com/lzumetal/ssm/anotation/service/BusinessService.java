package com.lzumetal.ssm.anotation.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * 业务Service
 */
@Service
public class BusinessService {

    private static final Logger log = LoggerFactory.getLogger(BusinessService.class);

    @Autowired
    private CacheService cacheService;


    public void doBusiness() {
        log.error("start to deal with our business");
        cacheService.cacheData();
        log.error("comlete service operation");
    }

    /**
     * 获取异步方法执行的返回值
     */
    public void doBusinessWithAsyncReturn() throws ExecutionException, InterruptedException {
        log.error("start to deal with our business");
        Future<String> future = cacheService.cacheDataWithReturn();
        log.error(future.get());
        log.error("comlete service operation");
    }
}
