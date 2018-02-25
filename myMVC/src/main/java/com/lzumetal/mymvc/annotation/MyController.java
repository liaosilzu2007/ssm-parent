package com.lzumetal.mymvc.annotation;

import java.lang.annotation.*;

/**
 * <p>Description:</p>
 *
 * @Author：liaosi
 * @Date: 2018-02-25
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyController {

    /**
     * 表示给controller注册别名
     * @return
     */
    String value() default "";

}
