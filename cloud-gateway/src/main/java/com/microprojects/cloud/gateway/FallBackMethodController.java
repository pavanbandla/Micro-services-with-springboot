package com.microprojects.cloud.gateway;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fallback")
public class FallBackMethodController {
	@GetMapping("/userServiceFallBackMethod")
	public ResponseEntity<String> userFallBackMethod(){
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body("Issue with user details please try again in 5 Seconds");
	}
	@GetMapping("/departmentServiceFallBackMethod")
	public ResponseEntity<String> departmentFallBackMethod(){
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body("something wrong in department section don't forgot to try again in 5 Seconds");
	}
}