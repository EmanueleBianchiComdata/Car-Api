package com.comdata.carService.dto;

import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;



public class CarDTO {

	
	private UUID id;
	
	@NotBlank(message = "brand is empty")
	@Size(min = 1, message = "brand is too short")
	private String brand;
	
	@NotBlank(message = "plate is empty")
	@Size(max = 7, message = "plate is too long")
	@Size(min = 1, message = "plate is too short")
	private String plate;

	public CarDTO() {
		super();
	}
	
	public CarDTO(String brand, String plate) {
		setBrand(brand);
		setPlate(plate);
	}
	
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
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
	
	
	
}
