package com.lzumetal.mymvc.annotation;

import java.lang.annotation.*;

/**
 * <p>Description:</p>
 *
 * @Author：liaosi
 * @Date: 2018-02-25
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyRequestMapping {

    /**
     * 表示访问该方法的url
     * @return
     */
    String value() default "";
}
