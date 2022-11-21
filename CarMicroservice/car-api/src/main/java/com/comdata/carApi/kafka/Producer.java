package com.comdata.carApi.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.comdata.carService.model.Car;

@Component
public class Producer {
	
	@Autowired
	private KafkaTemplate<String, Car> template;	
	
	public void writePost(Car car) {
		template.send("POST", car);
	}
	
	public void writePut(Car car) {
		template.send("PUT", car);
	}
	
	public void writeDelete(Car car) {
		template.send("DELETE", car);
	}
}
