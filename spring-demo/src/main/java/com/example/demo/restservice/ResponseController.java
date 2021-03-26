package com.example.demo.restservice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.car.Car;
import com.example.demo.car.Engine;
import com.fasterxml.jackson.databind.JsonNode;

@RestController
@RequestMapping("cars")
public class ResponseController {

	private final List<Car> cars;
	
	public ResponseController() {
		cars = new ArrayList<Car>(); 
		cars.add(new Car("1", new Engine("v6")));
		cars.add(new Car("2", new Engine("v8")));
		cars.add(new Car("3", new Engine("v12")));
	}
	
	@GetMapping("/all")
	public List<Car> getCarAll() {
		return cars;
	}
	
	@GetMapping("/car")
	public Car getCarRequestParam(@RequestParam(value = "id", defaultValue = "0") String id) {
		return cars.stream().filter(car -> id.equals(car.getID())).findFirst().orElse(null);
	}
	
	@GetMapping("/car/{id}")
	public Car getCarPathVar(@PathVariable(value = "id") String id) {
		return cars.stream().filter(car -> id.equals(car.getID())).findFirst().orElse(null);
	}
	
	@PostMapping(value="/add")
	public String addCar(@RequestBody JsonNode data) {
		String engineType = data.get("engine").get("type").asText();
		cars.add(new Car(Integer.toString(cars.size()+1), new Engine(engineType)));
	    return "car added";
	}
	
	@PostMapping(value="/servlet")
	public ResponseEntity<String> callback(HttpServletRequest request) {
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("header key", "header val");
		try {
			String data = "";
			data += "ip: " + request.getRemoteAddr() + "\n";
			data += "content: " + request.getReader().lines().collect(Collectors.joining());
			return ResponseEntity.ok().headers(responseHeaders).body(data);
		} catch(IOException e) {
			return ResponseEntity.status(HttpServletResponse.SC_INTERNAL_SERVER_ERROR)
					.headers(responseHeaders)
					.body("ERROR");
		}
    }
	
}
