package com.key.api.filter;

import java.io.IOException;
import javax.annotation.Resource;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

/**
 * @author kui
 * 
 * @version v0.1
 * 
 * Created on 2017-10-25
 * 
 * Revision History:
 * Date   		Reviser		Description
 * ----   		-------   	----------------------------------------------------
 * 
 * Description:自定义的过滤器.解决所有返回的数据格式.
 */
@Configuration
public class EncoderFilter implements Filter{
	@Override
	public void destroy() {
	}
	private Logger logger=LoggerFactory.getLogger(EncoderFilter.class);
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		 HttpServletRequest req=(HttpServletRequest)request;
		 if(req.getRequestURI().contains("services")){
			 logger.info("开始设置返回格式..{}",req.getRequestURI());
			 response.setCharacterEncoding("UTF-8");
		 }
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}  
	
}  