package com.example.demo.car;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Car {
	
	private static int counter = 0;
	
	private String id;
	private Engine engine;
	
	@Autowired
	public Car(String id, Engine engine) {
		this.id = id;
		this.engine = engine;
	}
	
	public String getID() {
		return id;
	}
	
	public Engine getEngine() {
		return engine;
	}
	
}
