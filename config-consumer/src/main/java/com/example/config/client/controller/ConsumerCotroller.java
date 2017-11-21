package com.example.config.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
@RestController
public class ConsumerCotroller {
    @Autowired
    private RestTemplate template;
     
    @RequestMapping("/consumer")
    public String index() {
    	// SERVICE-HI注册中心的服务名称.后面的都是路径.
        return template.getForEntity("http://SERVICE-HI/hi?name=Kui", String.class).getBody();
    }
    @RequestMapping("/test")
    public String test() {
        return " i am consumer thank you !";
    }
}