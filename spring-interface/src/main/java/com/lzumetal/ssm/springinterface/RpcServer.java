package com.lzumetal.ssm.springinterface;


import com.lzumetal.ssm.springinterface.annotation.MyRpcService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liaosi
 * @date 2018-08-05
 */
@Component
public class RpcServer implements ApplicationContextAware {


    /**
     * 存放服务名称与服务实例之间的映射关系
     */
    private Map<String, Object> handlerMap = new HashMap<>();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        //扫描带有MyPpcService注解的服务类
        Map<String, Object> serviceBeanMap = applicationContext.getBeansWithAnnotation(MyRpcService.class);

        //将服务类放入一个map中
        if (!CollectionUtils.isEmpty(serviceBeanMap)) {
            for (Object serviceBean : serviceBeanMap.values()) {
                MyRpcService myRpcService = serviceBean.getClass().getAnnotation(MyRpcService.class);
                String serviceName = myRpcService.value().getName();
                handlerMap.put(serviceName, serviceBean);
            }
        }
    }

    public Map<String, Object> getHandlerMap() {
        return handlerMap;
    }

    public void setHandlerMap(Map<String, Object> handlerMap) {
        this.handlerMap = handlerMap;
    }
}
