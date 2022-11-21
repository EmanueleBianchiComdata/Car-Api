package com.comdata.carApi.service;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.comdata.carApi.kafka.Producer;
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
	public Page<Car> showCarInDatabase(int page, int element) {
		
		try {	
			log.info("Show all Car");
			PageRequest request= PageRequest.of(page, element,Sort.by("brand"));
			Page<Car> paging = repository.findAll(request);
			return paging;		
			
		} catch (Exception e) {
			log.error("Error in Show all Car");
			return null;
		}
	}

	@Override
	public Car showOneCar(UUID id) {
		try {
			log.info("Show one Car");
			Car car = repository.findById(id).get();
			return car;
			
		} catch (Exception e) {
			log.error("Error in Show one Car");
			return null;
		}
	}

	@Override
	public void addCarInDatabase(Car car) {
		try {
			log.info("add Car");
			producer.writePost(car);
			
		} catch (Exception e) {
			log.error("Error in add Car");
		}
	}

	@Override
	public void updateCarInDatabse(Car car) {
		try {
			log.info("update Car");
			if(repository.findById(car.getId()).isPresent()) {
				producer.writePut(car);
			}
			else
				log.error("Car not found");
			
		} catch (Exception e) {
			log.info("error in update Car");
		}
	}

	@Override
	public void deleteCarInDatabase(Car car) {
		try {
			log.info("delete Car");
			if(repository.findById(car.getId()).isPresent()) {	
				producer.writeDelete(car);
			}
			else
				log.error("Car not found");
		} catch (Exception e) {
			log.error("Error in delete Car");
		}
	}
	
	
}
