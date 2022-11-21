package com.comdata.carWorker.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.comdata.carWorker.kafka.Producer;
import com.comdata.carService.model.Car;
import com.comdata.carService.repository.CarRepository;

@Service
public class CarServiceImpl implements CarService{

	private CarRepository repository;
	
	private Logger log = LoggerFactory.getLogger(CarServiceImpl.class);
	
	private Producer producer;
	
	public CarServiceImpl(CarRepository repository, Producer producer) {
		this.repository=repository;
		this.producer=producer;
	}
	
	@Override
	@KafkaListener(topics = "POST", groupId = "CarGroup")
	public void addCar(Car car) {
		repository.save(car);
		producer.writeTemplate("successful add");
		log.info("add car "+car);
	}
	
	@Override
	@KafkaListener(topics = "DELETE", groupId = "CarGroup")
	public void deleteCar(Car car) {
		repository.delete(car);
		producer.writeTemplate("successful delete");
		log.info("delete car "+car);
	}
	
	@Override
	@KafkaListener(topics = "PUT", groupId = "CarGroup")
	public void updateCar(Car car) {
		repository.save(car);
		producer.writeTemplate("successful update");
		log.info("update car "+car);
	}
	
}