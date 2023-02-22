package com.in28minutes.microservices.currencyexchangeservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.retry.annotation.Retry;

@RestController
public class CircuitBreakerController {
	
	private Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);

	@GetMapping("/circuit-breaker")
	@Retry(name = "circuit-breaker")
	public String dummyApi() {
		logger.info("Circuit breaker call received");
		ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:8080/not-working-endpoint", String.class);
		return forEntity.getBody();
	}
}
