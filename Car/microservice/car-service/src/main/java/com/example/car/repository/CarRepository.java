package com.example.car.repository;

import java.util.UUID;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.car.model.Car;

public interface CarRepository extends PagingAndSortingRepository<Car, UUID>{

}
