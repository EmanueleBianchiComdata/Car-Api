package com.comdata.carService.repository;

import java.util.UUID;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.comdata.carService.model.Car;

public interface CarRepository extends PagingAndSortingRepository<Car, UUID>{

}
