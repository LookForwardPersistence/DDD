package com.dawn.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Created by Dawn on 2020/4/2.
 */
@Configuration

@EnableAspectJAutoProxy
public class BaseConfiguration {

    /**
     * 初始化spring上下文持有工具
     * */
    @Bean
    public SpringContextHolder springContextHolder(){
        return new SpringContextHolder();
    }
}
