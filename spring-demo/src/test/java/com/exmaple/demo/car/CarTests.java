package com.exmaple.demo.car;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.demo.car.Car;
import com.example.demo.car.CarConfig;

class CarTests {

	@Test
	void test() {
		ApplicationContext context = new AnnotationConfigApplicationContext(CarConfig.class);
		Car car = context.getBean(Car.class);
		assertEquals(car.getEngine().getType(), "v8");
	}

}
