package com.key.api.base;

import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
@Component
@Order(-5)
public class WebLogAspect {

	private static final Logger logger = LoggerFactory.getLogger(WebLogAspect.class);

	/**
	 * 定义一个切入点. 解释下：
	 * ~ 第一个 * 代表任意修饰符及任意返回值. 
	 * ~ 第二个 * 任意包名 
	 * ~ 第三个 * 代表任意方法. 
	 * ~ 第四个 * 定义在controller包或者子包 
	 * ~ 第五个 * 任意方法 
	 * ~ .. 匹配任意数量的参数.
	 */
	@Pointcut("execution(public * com.gnet..controller..*.*(..))")
	public void webLog() {
	}

	@Before("webLog()")
	public void doBefore(JoinPoint joinPoint) throws Throwable {

		// 接收到请求，记录请求内容
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		HttpServletResponse response = attributes.getResponse();
		// 记录下请求内容
		logger.info("请求地址 : " + request.getRequestURL().toString());
		logger.info("HTTP_METHOD : {}", request.getMethod());
		logger.info("IP : " + request.getRemoteAddr());
		logger.info("CLASS_METHOD : {},{},{}", joinPoint.getSignature().getDeclaringTypeName() , "."
				, joinPoint.getSignature().getName());
		// 获取所有参数方法一：
//		logger.info("参数 : " + Arrays.toString(joinPoint.getArgs()));
		logger.info("参数 : ");
		Enumeration<String> enu = request.getParameterNames();
		while (enu.hasMoreElements()) {
			String paraName = (String) enu.nextElement();
			logger.info(paraName + ": {}", request.getParameter(paraName));
		}
//		logger.info("SESSION : {}", request.getSession().getAttribute("city"));
//		response.sendRedirect("/");
	}

//	@AfterReturning(returning = "ret", pointcut = "webLog()")
//	public void doAfterReturning(Object ret) throws Throwable {
//		// 处理完请求，返回内容
//		logger.info("返回值 : " + ret);
//	}
	
	@Around("webLog()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object ob = pjp.proceed();// ob 为方法的返回值
        logger.info("耗时 : " + (System.currentTimeMillis() - startTime));
        return ob;
    }

}
