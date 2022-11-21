package com.comdata.carApi.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
	
	private Logger log= LoggerFactory.getLogger(Consumer.class);

	@KafkaListener(topics = "MessageTopic", groupId = "CarGroup")
	public void getMessage(String mex) {
		log.info(mex);
	}
	
}
