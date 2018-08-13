package com.lzumetal.ssm.anotation.test;

import com.lzumetal.ssm.anotation.service.BusinessService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-context.xml"})
public class MainTest {


    @Autowired
    private BusinessService businessService;


    @Test
    public void test() throws InterruptedException {
        businessService.doBusiness();
        TimeUnit.SECONDS.sleep(5L);     //不让主线程过早结束，否则控制台看不到异步方法中的输出内容
    }

    @Test
    public void testAsyncReturn() throws Exception {
        businessService.doBusinessWithAsyncReturn();
        TimeUnit.SECONDS.sleep(5L);
    }

}
