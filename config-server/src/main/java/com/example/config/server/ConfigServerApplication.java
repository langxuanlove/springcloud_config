package com.example.config.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
/**
 * 
 * 此类提供启动服务注册中心,并且提供config配置文件获取功能.
 * @author Kui
 *
 */
// 启动服务注册中心功能
@EnableEurekaServer
// 启用服务配置功能
//@EnableConfigServer
@SpringBootApplication
public class ConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigServerApplication.class, args);
	}
}
