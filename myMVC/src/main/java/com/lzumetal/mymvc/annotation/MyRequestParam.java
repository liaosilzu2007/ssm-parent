package com.lzumetal.mymvc.annotation;

import java.lang.annotation.*;

/**
 * <p>Description:</p>
 *
 * @Author：liaosi
 * @Date: 2018-02-25
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyRequestParam {

    /**
     * 表示参数的别名，必填
     * @return
     */
    String value();
}
