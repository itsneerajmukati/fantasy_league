package com.nm.match;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan("com.nm.*")
@ComponentScan(basePackages = "com.nm.*")
public class MatchServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MatchServiceApplication.class, args);
	}

}
