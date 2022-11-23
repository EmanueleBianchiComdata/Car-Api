package com.comdata.carWorker.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.comdata.carService.model.Car;
import com.comdata.carService.repository.CarRepository;

@Service
public class CarServiceImpl implements CarService{

	private CarRepository repository;
	
	private Logger log = LoggerFactory.getLogger(CarServiceImpl.class);
	
	
	public CarServiceImpl(CarRepository repository) {
		this.repository=repository;
	}
	
	@Override
	@KafkaListener(topics = "POST", groupId = "CarGroup")
	public Car addCar(Car car) {
		repository.save(car);
		log.info("add car "+car);
		return car;
	}
	
	@Override
	@KafkaListener(topics = "DELETE", groupId = "CarGroup")
	public Car deleteCar(Car car) {
		repository.delete(car);
		log.info("delete car "+car);
		return car;
	}
	
	@Override
	@KafkaListener(topics = "PUT", groupId = "CarGroup")
	public Car updateCar(Car car) {
		repository.save(car);
		log.info("update car "+car);
		return car;
	}
	
}