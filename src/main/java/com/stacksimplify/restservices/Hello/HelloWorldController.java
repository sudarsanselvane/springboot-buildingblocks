package com.stacksimplify.restservices.Hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//Controller
@RestController
public class HelloWorldController {
	
	// Simple Method
	// URI - /helloworld
	// GET
	//@RequestMapping(method = RequestMethod.GET, path = "/helloworld")
	@GetMapping("/helloworld1")
	public String helloWorld() {
		return "Hello World1";
		
	}
	
	@GetMapping("/helloworld-bean")
	public UserDetails helloWorldBean() {
		System.out.println("testing:" + new UserDetails("Sudar", "Selvane", "Charlotte"));
		return new UserDetails("Sudar", "Selvane", "Charlotte");
	}
	
	
}
