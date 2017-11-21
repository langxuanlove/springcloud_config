package com.example.config.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.hateoas.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
// 启动配置刷新功能.
//@RefreshScope
@RestController
@ExposesResourceFor(Customer.class)
public class DemoController {
	
//	@Value("${foo}")
//	String foo;
	@Value("${demo.env}")
	String value;
//	@RequestMapping(value = "/hi")
//	public String hi(){
//		return foo;
//	}
	@GetMapping("/demo")
	public String demo(){
		return value;
	}
   @Value("${server.port}")
    String port;
    @RequestMapping("/hi")
    public String home(@RequestParam String name) {
        return "hi "+name+",i am from port:" +port;
    }
    @Autowired
    private RestTemplate template;
     
    @RequestMapping("/test")
    public String index() {
    	// CONFIG-CONSUMER注册中心的服务名称.后面的都是路径.
        return template.getForEntity("http://CONFIG-CONSUMER/test", String.class).getBody();
    }
    
    
    private final CustomerRepository repository;

	private final EntityLinks entityLinks;

	public DemoController(CustomerRepository repository, EntityLinks entityLinks) {
		this.repository = repository;
		this.entityLinks = entityLinks;
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	HttpEntity<Resources<Customer>> showCustomers() {
		Resources<Customer> resources = new Resources<Customer>(this.repository.findAll());
		resources.add(this.entityLinks.linkToCollectionResource(Customer.class));
		return new ResponseEntity<Resources<Customer>>(resources, HttpStatus.OK);
	}

	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	HttpEntity<Resource<Customer>> showCustomer(@PathVariable Long id) {
		Resource<Customer> resource = new Resource<Customer>(this.repository.findOne(id));
		resource.add(this.entityLinks.linkToSingleResource(Customer.class, id));
		return new ResponseEntity<Resource<Customer>>(resource, HttpStatus.OK);
	}
}
