package com.lzumetal.ssm.springinterface.annotation;

import org.springframework.stereotype.Service;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * rpc服务注解（标注在服务实现类上）
 *
 * @author liaosi
 * @date 2018-08-05
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Service
public @interface MyRpcService {

    /*
     * 服务接口类
     */
    Class<?> value();

}
