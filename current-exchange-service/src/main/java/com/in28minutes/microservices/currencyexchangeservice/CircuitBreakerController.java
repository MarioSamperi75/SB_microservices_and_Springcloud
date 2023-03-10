package com.in28minutes.microservices.currencyexchangeservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
public class CircuitBreakerController {
	
	private Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);
	int counter = 0;

	@GetMapping("/circuit-breaker")
	//@Retry(name = "circuit-breaker", fallbackMethod = "hardcodedResponse")
	//@CircuitBreaker(name = "default", fallbackMethod = "hardcodedResponse")
	@RateLimiter(name="prova")
	public String dummyApi() {
		//logger.info("Circuit breaker call received");
		//ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:8080/not-working-endpoint", String.class);
		//return forEntity.getBody();
		counter++;
		return "Testing RateLimter " + counter	;
	}
	
	public String hardcodedResponse (Exception ex) {
		return "fallback-response";
	}
}
