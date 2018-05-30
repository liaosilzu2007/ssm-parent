package com.lzumetal.ssm.anotation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 业务服务
 */
@Service
public class BusinessService {

    @Autowired
    private CacheService cacheService;


    public void doCustomBusiness() {
        System.out.println("deal our custom business");
        cacheService.cacheData();
        System.out.println("comlete a operation");
    }
}
