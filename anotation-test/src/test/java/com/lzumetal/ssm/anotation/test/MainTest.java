package com.lzumetal.ssm.anotation.test;

import com.lzumetal.ssm.anotation.service.CacheService;
import com.lzumetal.ssm.anotation.service.BusinessService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-context.xml"})
public class MainTest {

    @Autowired
    private CacheService cacheService;

    @Autowired
    private BusinessService businessService;


}
