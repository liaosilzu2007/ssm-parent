package com.lzumetal.ssm.paramcheck.aspect;

import com.lzumetal.ssm.paramcheck.annotation.NotEmpty;
import com.lzumetal.ssm.paramcheck.annotation.NotNull;
import com.lzumetal.ssm.paramcheck.annotation.ValidParam;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Field;
import java.lang.reflect.Parameter;
import java.util.Arrays;

/**
 * 参数检查切面类
 */
@Aspect
@Component
public class ParamCheckAspect {

    @Before("execution(* com.lzumetal.ssm.paramcheck.controller.*.*(..))")
    public void paramCheck(JoinPoint joinPoint) throws Exception {
        //获取参数对象
        Object[] args = joinPoint.getArgs();
        //获取方法参数
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Parameter[] parameters = signature.getMethod().getParameters();
        for (int i = 0; i < parameters.length; i++) {
            Parameter parameter = parameters[i];
            //Java自带基本类型的参数（例如Integer、String）的处理方式
            if (isPrimite(parameter.getType())) {
                NotNull notNull = parameter.getAnnotation(NotNull.class);
                if (notNull != null && args[i] == null) {
                    throw new RuntimeException(parameter.toString() + notNull.msg());
                }
                //TODO
                continue;
            }
            /*
             * 没有标注@ValidParam注解，或者是HttpServletRequest、HttpServletResponse、HttpSession时，都不做处理
            */
            if (parameter.getType().isAssignableFrom(HttpServletRequest.class) || parameter.getType().isAssignableFrom(HttpSession.class) ||
                    parameter.getType().isAssignableFrom(HttpServletResponse.class) || parameter.getAnnotation(ValidParam.class) == null) {
                continue;
            }
            Class<?> paramClazz = parameter.getType();
            //获取类型所对应的参数对象，实际项目中Controller中的接口不会传两个相同的自定义类型的参数，所以此处直接使用findFirst()
            Object arg = Arrays.stream(args).filter(ar -> paramClazz.isAssignableFrom(ar.getClass())).findFirst().get();
            //得到参数的所有成员变量
            Field[] declaredFields = paramClazz.getDeclaredFields();
            for (Field field : declaredFields) {
                field.setAccessible(true);
                //校验标有@NotNull注解的字段
                NotNull notNull = field.getAnnotation(NotNull.class);
                if (notNull != null) {
                    Object fieldValue = field.get(arg);
                    if (fieldValue == null) {
                        throw new RuntimeException(field.getName() + notNull.msg());
                    }
                }
                //校验标有@NotEmpty注解的字段，NotEmpty只用在String类型上
                NotEmpty notEmpty = field.getAnnotation(NotEmpty.class);
                if (notEmpty != null) {
                    if (!String.class.isAssignableFrom(field.getType())) {
                        throw new RuntimeException("NotEmpty Annotation using in a wrong field class");
                    }
                    String fieldStr = (String) field.get(arg);
                    if (StringUtils.isBlank(fieldStr)) {
                        throw new RuntimeException(field.getName() + notEmpty.msg());
                    }
                }
            }
        }
    }

    /**
     * 判断是否为基本类型：包括String
     * @param clazz clazz
     * @return  true：是;     false：不是
     */
    private boolean isPrimite(Class<?> clazz){
        return clazz.isPrimitive() || clazz == String.class;
    }

}
