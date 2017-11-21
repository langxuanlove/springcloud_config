package com.key.api.cxfconfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
/**
 * 创建上下文工具类.
 * 
 * @author kui
 *
 */
@Component("springBeanUtil")  
@Order(0)
public class SpringBeanContext implements ApplicationContextAware {  
	private Logger logger=LoggerFactory.getLogger(getClass());
	public SpringBeanContext(){
		logger.info("SpringBeanUtil实例化已经......");
	}
    private static ApplicationContext ctx = null;  
  
    public void setApplicationContext(ApplicationContext ctx)  
            throws BeansException {  
    	SpringBeanContext.ctx = ctx;  
    }  
  
    public static ApplicationContext getApplicationContext() {  
        return ctx;  
    }  
    @SuppressWarnings("unchecked")
	public  static <T> T getBean(String prop) {  
        Object obj = ctx.getBean(prop);  
        return (T)obj;  
    }  
  
 
}  
