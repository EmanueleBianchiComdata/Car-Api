package com.comdata.carService.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "Car")
public class Car {

	@Id
	@GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name="id")
	private UUID id;
	
	@Column(name = "brand")
	private String brand;
	
	@Column(name = "plate", unique = true)
	private String plate;
	
	
	
	public Car() {
		super();
	}

	public Car(UUID id,String brand, String plate) {
		setBrand(brand);
		setPlate(plate);
	}
	
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id=id;
	}
	
	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getPlate() {
		return plate;
	}

	public void setPlate(String plate) {
		this.plate = plate;
	}
	
	@Override
	public String toString() {
		return this.getId()+" "+this.getBrand()+" "+this.getPlate();
	}
	
}
