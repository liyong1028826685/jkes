package com.timeyang.jkes.spring.jpa;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.inject.Named;

/**
 * @author chaokunyang
 */
@Named
public class ContextSupport implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public synchronized void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        notifyAll();
    }

    public synchronized  Object getBean(String beanName) throws InterruptedException {
        if(this.applicationContext == null)
            wait();
        return this.applicationContext.getBean(beanName);
    }

    public synchronized Object getBean(Class<?> requiredType) throws InterruptedException {
        if(this.applicationContext == null)
            wait();
        return this.applicationContext.getBean(requiredType);
    }
}
