package com.lzumetal.ssm.paramcheck.aspect;

import com.lzumetal.ssm.paramcheck.annotation.NotNull;
import com.lzumetal.ssm.paramcheck.annotation.ValidParam;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class ParamCheckAspect {

    @Before("execution(* com.lzumetal.ssm.paramcheck.controller.*.*(..))")
    public void paramCheck(JoinPoint joinPoint) throws Exception {
        Object[] args = joinPoint.getArgs();
        Map<String, Object> argsMap = new HashMap<>();
        for (Object arg : args) {
            argsMap.put(arg.getClass().getName(), arg);
        }
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Parameter[] parameters = method.getParameters();
        for (Parameter parameter : parameters) {
            //Annotation[] annotations = parameter.getAnnotations();
            if (parameter.getAnnotation(ValidParam.class) != null) {
                Class<?> paramClazz = parameter.getType();
                Object arg = argsMap.get(paramClazz.getName());
                Field[] declaredFields = paramClazz.getDeclaredFields();
                for (Field field : declaredFields) {
                    NotNull annotation = field.getAnnotation(NotNull.class);
                    if (annotation != null) {
                        field.setAccessible(true);
                        Integer integer = (Integer) field.get(arg);
                        if (integer == null) {
                            throw new RuntimeException(field.getName() + annotation.msg());
                        }
                    }
                }

            }
           /* for (Annotation annotation : annotations) {
                if (ValidParam.class.isAssignableFrom(annotation.annotationType())) {

                }
            }*/

        }


    }
}
