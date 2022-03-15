package com.example.rest_proj.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringAppApplicationContext implements ApplicationContextAware {

    private static ApplicationContext CONTEXT;
    @Override
    public void setApplicationContext(org.springframework.context.ApplicationContext applicationContext) throws BeansException {
        CONTEXT = applicationContext;
    }
    public static Object getBean(String beanName){
        return CONTEXT.getBean(beanName);
    }
}
