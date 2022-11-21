package com.comdata.carWorker.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class Producer {

	private String topic="MessageTopic";
	
	@Autowired
	private KafkaTemplate<String, String> template;
	
	public void writeTemplate(String message) {
		template.send(topic, message);
	}
	
}
