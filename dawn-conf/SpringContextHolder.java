package com.dawn.conf;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Map;

/**
 * Created by Dawn on 2020/4/2.
 * 静态变量保存 Spring ApplicationContext
 * 可在任何地方任何时候获取ApplicaionContext
 */
public class SpringContextHolder implements ApplicationContextAware {

    private static ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
          SpringContextHolder.applicationContext = applicationContext;
    }

    /**
     *  获取上下文
     * @param
     * @return getApplicationContext
     * @author fanqianghua
     * @date 2020/4/2
     */
    public ApplicationContext getApplicationContext(){
        checkApplicationContext();
        return applicationContext;
    }

    /**
     *  从静态变量ApplicationContext中获取Bean ，自动转型为赋值对象类型
     * @param name
     * @return getBean
     * @author fanqianghua
     * @date 2020/4/2
     */
    public static <T> T getBean(String name){
        checkApplicationContext();
        return (T) applicationContext.getBean(name);
    }

    /**
     *  如果有多个Bean符合Class，取第一个
     * @param clazz
     * @return getBean
     * @author fanqianghua
     * @date 2020/4/2
     */
    public static <T> T getBean(Class<T> clazz){
        checkApplicationContext();
        Map beansMap = applicationContext.getBeansOfType(clazz);
        if(beansMap!=null && !beansMap.isEmpty()){
            return (T) beansMap.values().iterator().next();
        }else {
            return null;
        }
    }
    private static void checkApplicationContext(){
        if(applicationContext==null){
            throw new IllegalStateException("applicationContext未注入");
        }
    }
}
