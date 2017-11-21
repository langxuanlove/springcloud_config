package com.key.api.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 部署服务器的数据库服务器配置信息，用于配置独立部署时的一些数据，以后可能在数据库中保存，不在这里书写
 * @author aaron
 *
 */
public class DBConfig {
	
	private static final String DB_IP = "192.168.1.43";
	
	private static final String DB_PORT = "3306";
	
	private static final String DB_USER = "root";
	
	private static final String DB_PWD = "123456";
	
	public static Map<String, String> getConfig() {
		
		Map<String, String> map = new HashMap<>();
		map.put("DB_IP", DB_IP);
		map.put("DB_PORT", DB_PORT);
		map.put("DB_USER", DB_USER);
		map.put("DB_PWD", DB_PWD);
		return map;
	}

}