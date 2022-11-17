package com.example.car.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
	
	private Logger log= LoggerFactory.getLogger(Consumer.class);

	@KafkaListener(topics = "CarTopic", groupId = "CarGroup")
	public void getCar(String car) {
		log.info(car);
	}
	
}
