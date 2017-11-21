package com.key.api.util;

import java.util.HashMap;
import java.util.Map;

public class DeployConfig {
	
	public static final String DEPLOY_SERVICE_URL_KEY = "deploy_service_url";
	
	public static final String DEPLOY_SERVICE_IP_KEY = "deploy_service_ip";
	
	public static Map<String, String> getConfig() {
		
		Map<String, String> map = new HashMap<>();
		map.put(DEPLOY_SERVICE_URL_KEY, "http://192.168.4.80:8090/deployservice/deploy");
		map.put(DEPLOY_SERVICE_IP_KEY, "http://192.168.4.80:8090");
		return map;
	}

}