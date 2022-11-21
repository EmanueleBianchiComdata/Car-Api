package com.comdata.carApi.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.comdata.carApi.component.CarComponent;
import com.comdata.carService.dto.CarDTO;
import com.comdata.carService.model.Car;
import com.comdata.carApi.service.CarService;

@RestController
public class CarController {
	
	private CarComponent component;
	private CarService service;
	
	public CarController(CarComponent component, CarService service) {
		this.component=component;
		this.service=service;
	}
	
	@GetMapping("/car")
	public Page<CarDTO> showAllCar(@RequestParam("page") int page, @RequestParam("element") int element) {
		Page<Car> listCar =service.showCarInDatabase(page, element);
		List<Car> cars=listCar.toList();
		List<CarDTO> carsDto= new ArrayList<>();
		for(int i=0;i<cars.size();i++) {
			CarDTO carDto = component.ModelToDto(cars.get(i));
			carsDto.add(carDto);			
		}
		Page<CarDTO> pageCarDto= new PageImpl<>(carsDto, PageRequest.of(page, element), Long.valueOf(carsDto.size()) );
		return pageCarDto;
	}
	
	@GetMapping("/car/{id}")
	public CarDTO showCarById(@PathVariable UUID id) {
		Car car= service.showOneCar(id);
		CarDTO carDto= component.ModelToDto(car);
		return carDto;
	}
	
	
	@DeleteMapping("/car")
	public void deleteCar(@Valid @RequestBody CarDTO carDto) {
		Car car = component.DtoToModel(carDto);
		service.deleteCarInDatabase(car);
	}
	
	@PutMapping("/car")
	public void updateCar(@Valid @RequestBody CarDTO carDto) {
		Car car= component.DtoToModel(carDto);
		service.updateCarInDatabse(car);
	}
	
	@PostMapping("/car")
	public void insertCar(@Valid @RequestBody CarDTO carDto) {
		Car car= component.DtoToModel(carDto);
		service.addCarInDatabase(car);
	}
	
}
