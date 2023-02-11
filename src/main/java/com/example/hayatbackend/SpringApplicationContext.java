package com.example.hayatbackend;


import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 *
 * @author hayat
 */
public class SpringApplicationContext implements ApplicationContextAware {
    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext ac) throws BeansException {
     context = ac;
    }
    public static Object getBean(String beanName) {
        return context.getBean(beanName);
    }
    

    
    
}
