package com.example.car.service;

import java.util.UUID;

import org.springframework.data.domain.Page;

import com.example.car.model.Car;

public interface CarService {

	public Page<Car> showCarInDatabase(int page, int element);
	
	public Car showOneCar(UUID id);
	
	public void addCarInDatabase(Car car); 
	
	public void updateCarInDatabse(Car car);
	
	public void deleteCarInDatabase(Car car);
	
}
