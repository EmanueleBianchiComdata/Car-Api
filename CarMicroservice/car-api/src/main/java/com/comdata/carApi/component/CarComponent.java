package com.comdata.carApi.component;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.comdata.carService.dto.CarDTO;
import com.comdata.carService.model.Car;

@Component
public class CarComponent {

	 private ModelMapper mapper;
	
	public CarComponent(ModelMapper mapper) {
		this.mapper=mapper;
	}
	
	public Car DtoToModel(CarDTO carDto) {
		Car car = mapper.map(carDto, Car.class);
		return car;
	}
	
	public CarDTO ModelToDto(Car car) {
		CarDTO cardto= mapper.map(car, CarDTO.class);
		return cardto;
	}
	
}
