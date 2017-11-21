package com.key.api.ws.impl;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
/**
 * 
 * 切换数据源Advice(AOP) ,获取数据进行切换数据源.
 * @author Kui
 *
 */

@Aspect
@Order(-1)// 保证该AOP在@Transactional之前执行
@Component
public class DynamicDataSourceAspect {

    private static final Logger logger = LoggerFactory.getLogger(DynamicDataSourceAspect.class);
    // service包下的所有方法.
    @Pointcut(value="execution(* com.gnet.api.service..*.*(..))")
    private void serviceMethod(){} 
    // 拦截iom下的tag包的所有方法.
    @Pointcut(value="execution(* com.gnet.api.iom..*.*(..))")
    
    private void iomMethod(){}
    
    @Before(value="iomMethod()")
    public void changeDataSource(JoinPoint point) throws Throwable {
    	JSONObject jsonParam=null;
    	String cloudId=null;
    	// 在此处解析传入的参数进而得到私有云.
    	Object [] obj=point.getArgs();
    	if(obj[0]!=null){
    		jsonParam=JSONObject.parseObject(String.valueOf(obj[0]));
    		cloudId=JSONObject.parseObject(jsonParam.getString("IOM_Head")).getString("cloudId");
    	}
    	if(DynamicDataSourceContextHolder.containsDataSource(cloudId)){
    		// 设置所要引用的数据源.
    		DynamicDataSourceContextHolder.setDataSourceType(cloudId);
    		logger.info("连接的数据源是私有云{}",cloudId); 
    	}else{
    		logger.info("使用默认数据源......云id:{}",cloudId);
    	}
    }

    @After(value="iomMethod()")
    public void restoreDataSource(JoinPoint point) {
        DynamicDataSourceContextHolder.clearDataSourceType();
    }

}
