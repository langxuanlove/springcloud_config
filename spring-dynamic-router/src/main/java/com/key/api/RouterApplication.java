package com.key.api;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication 
public class RouterApplication  {

	protected SpringApplicationBuilder configure(
			SpringApplicationBuilder application) { 
		return application.sources(RouterApplication.class);
	}
 
	public static void main(String[] args) {
		SpringApplication.run(RouterApplication.class, args);
	}
}
