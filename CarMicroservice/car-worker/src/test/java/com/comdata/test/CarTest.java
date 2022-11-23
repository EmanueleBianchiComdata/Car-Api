package com.comdata.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.comdata.carService.model.Car;
import com.comdata.carService.repository.CarRepository;
import com.comdata.carWorker.service.CarServiceImpl;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class CarTest {

	@MockBean
	private CarRepository repository;
	
	@InjectMocks
	private CarServiceImpl service;
	
	@Test
	public void testCreate() {
		UUID id= UUID.randomUUID();
		Car car=new Car(id,"Fiat", "qwertyu");
		Mockito.when(repository.save(car)).thenReturn(car);
		Car carInsert= service.addCar(car);
		assertEquals(car,carInsert);
	}
	
	@Test
	public void testDelete() {
		UUID id= UUID.randomUUID();
		Car car=new Car(id,"Fiat", "qwertyu");
		Mockito.when(repository.save(car)).thenReturn(car);
		Car carInsert= service.deleteCar(car);
		assertEquals(car,carInsert);
	}
	
	@Test
	public void testUpdate() {
		UUID id= UUID.randomUUID();
		Car car=new Car(id,"Fiat", "qwertyu");
		Mockito.when(repository.save(car)).thenReturn(car);
		Car carInsert= service.updateCar(car);
		assertEquals(car,carInsert);
	}
}
