package com.comdata.carWorker.service;

import com.comdata.carService.model.Car;

public interface CarService {

	public Car addCar(Car car);
	
	public Car updateCar(Car car);
	
	public Car deleteCar(Car car);
}
