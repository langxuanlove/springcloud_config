package com.key.api.cxfconfig;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.Filter;
import javax.xml.ws.Endpoint;
import org.apache.cxf.Bus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.swagger.Swagger2Feature;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.filter.CharacterEncodingFilter;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.key.api.ws.impl.IWsCommonImpl;

/**
 * 
 * @author kui
 *
 */
@Configuration 
public class CxfConfig {
		// 使用注解形式就所有的bean都交给spring去管理，自己不做new Object();
	    @Resource
		private IWsCommonImpl WsCommonImpl; 
	  	@Autowired
	    private Bus bus;
	  	private Logger logger=LoggerFactory.getLogger(getClass());
	  	/**
	  	 * 发布cxf的rest接口
	  	 * @return
	  	 */
	    @Bean
	    public Server rsServer() {
	        JAXRSServerFactoryBean endpoint = new JAXRSServerFactoryBean();
	        endpoint.setBus(bus);
	        // 此处是有几个接口bean就实例化几个.使用注解形式就所有的bean都交给spring去管理，自己不做new Object();
	        endpoint.setServiceBeans(Arrays.<Object>asList(this.WsCommonImpl));
	        // 此处必须区别不能为根节点.也就是不能设置和cxf发布webservice的相同的根
	        // 路径,因此设置为/IOM_CommonRest在请求的实际路径是
	        // 'http://ip:端口/项目名称/services/IOM_CommonRest/具体方法名称',即可访问rest请求.
	        endpoint.setAddress("/CommonRest");
	    	List<Object> providerList = new ArrayList<Object>();
	    	providerList.add(new JacksonJsonProvider());
	    	endpoint.setProviders(providerList);
	        endpoint.setFeatures(Arrays.asList(createSwaggerFeature(), new org.apache.cxf.feature.LoggingFeature()));
	        return endpoint.create();
	    }
	    /**
	     * swagger的注解文档.
	     * @return
	     */
	    @Bean
	    public Swagger2Feature createSwaggerFeature() {
	        Swagger2Feature swagger2Feature = new Swagger2Feature();
	        swagger2Feature.setPrettyPrint(true);
	        swagger2Feature.setTitle("Restful 接口");
	        swagger2Feature.setContact("");
	        swagger2Feature.setDescription(".");
	        swagger2Feature.setVersion("1.0.0");
	        return swagger2Feature;
	    }
	    /**
	     * 发布cxf的webservice接口.（普通的）
	     * 
	     * @return
	     */
	    //@Bean
	    public Endpoint endpoint() {
	        EndpointImpl endpoint = new EndpointImpl(bus, this.WsCommonImpl);
	        endpoint.publish("/CommonService");
	        return endpoint;
	    }
	    /**
	     * 工厂模式发布webservice
	     * @return
	     * @throws Exception
	     */
	    @Bean 
	    protected JaxWsServerFactoryBean server() throws Exception {
	    	logger.info("Starting Server");
	        JaxWsServerFactoryBean wsFactory = new JaxWsServerFactoryBean();
	        wsFactory.setServiceClass(IWsCommonImpl.class);
	        wsFactory.setAddress("/API_WebService");
	        wsFactory.setServiceBean(this.WsCommonImpl);
	        Map<String,Object> properties = new HashMap<String,Object>();
		    properties.put("Content-Type", "application/json");
	        wsFactory.setProperties(properties);
	        wsFactory.getInInterceptors().add(new LoggingInInterceptor() );
	        wsFactory.getOutInterceptors().add(new LoggingOutInterceptor());
	        wsFactory.create();
			return wsFactory;
	    }
	    
	    /**
	     *  UTF-8编码
	     *  
	     * @return
	     */
	    @Bean  
	    public Filter characterEncodingFilter() {  
	        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();  
	        characterEncodingFilter.setEncoding("UTF-8");  
	        characterEncodingFilter.setForceEncoding(true);  
	        return characterEncodingFilter;  
	    }  
	    /**
	     *  UTF-8编码
	     *  
	     * @return
	     */
	    @Bean
	    public StringHttpMessageConverter converter(){
	    	StringHttpMessageConverter httpMessageConverter=new StringHttpMessageConverter();
	    	httpMessageConverter.setDefaultCharset(Charset.forName("UTF-8"));
	    	return httpMessageConverter;
	    }
	    
}
