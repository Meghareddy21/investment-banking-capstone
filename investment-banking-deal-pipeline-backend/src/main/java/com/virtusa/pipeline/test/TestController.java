package com.virtusa.pipeline.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@GetMapping("/api/test")
	public String test() {
	    System.out.println("Test API called");
	    return "JWT is working!";
	}

}
