package com.key.api.util;

import java.nio.charset.StandardCharsets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * Spring RestTemplate工具类
 * 
 * @author zhang
 *
 */
public class RestTemplateUtils {

	private static class SingletonRestTemplate {
		/**
		 * 单例对象实例
		 */
		static final RestTemplate INSTANCE = new RestTemplate();
	}

	private RestTemplateUtils() {

	}

	/**
	 * 单例实例
	 */
	public static RestTemplate getInstance() {
		SingletonRestTemplate.INSTANCE.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
		return SingletonRestTemplate.INSTANCE;
	}
	
	@Autowired
	public static HttpEntity<String> HeadersUrlencoded(String request) {
		
		HttpHeaders headers = new HttpHeaders();
		MediaType type = MediaType.parseMediaType("application/x-www-form-urlencoded;charset=UTF-8");
		headers.setContentType(type);
		
		return new HttpEntity<String>(request, headers);
	}
	
//	@Autowired
//	public static <T> ResponseRootData<T> ResponseIomList(Object reponeData) {
//		
//		ObjectMapper mapper = new ObjectMapper();
//			
//		TypeReference ref = new TypeReference<ResponseRootData<T>>() { };
//		ResponseRootData<ResponseOrganByUserId> rd;
//		try {
//			rd = mapper.readValue((JsonParser) reponeData, ref);
//		
//			return (ResponseRootData<T>) rd;
//			
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return null;
//		}
//
//	}

}
