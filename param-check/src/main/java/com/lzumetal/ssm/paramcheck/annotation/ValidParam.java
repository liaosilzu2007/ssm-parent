package com.lzumetal.ssm.paramcheck.annotation;

import java.lang.annotation.*;

/**
 * 标注在参数bean上，表示需要对该参数校验
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ValidParam {


}
