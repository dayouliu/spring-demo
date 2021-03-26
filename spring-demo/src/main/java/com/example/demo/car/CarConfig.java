package com.example.demo.car;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = Car.class)
public class CarConfig {
	
	@Bean String id() {
		return "1";
	}
	
	@Bean
	public Engine engine() {
		return new Engine("v8");
	}
	

}
