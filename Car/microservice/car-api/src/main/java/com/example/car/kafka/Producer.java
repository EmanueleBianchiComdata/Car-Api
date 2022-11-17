package com.example.car.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class Producer {

	private String topic="CarTopic";
	
	@Autowired
	private KafkaTemplate<String, String> template;
	
	public void writeTemplate(String car) {
		template.send(topic, car);
	}
	
}
