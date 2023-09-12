package com.devsuperior.dscommerce.dtos;

import com.devsuperior.dscommerce.entities.Product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class ProductDto {

	private Long id;
	@Size(min=3, max=80, message="The name must have between 3 and 80 characters")
	@NotBlank(message = " Field required")
	private String name;
	@Size(min=10, message="The description must have above 10 characters")
	@NotBlank(message = " Field required")
	private String description;
	@Positive(message = "The price must be postive")
	private Double price;
	private String imgUrl;

	public ProductDto(Long id, String name, String description, Double price, String imgUrl) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.imgUrl = imgUrl;
	}

	public ProductDto(Product entity) {
		id = entity.getId();
		name = entity.getName();
		description = entity.getDescription();
		price = entity.getPrice();
		imgUrl = entity.getImgUrl();
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public Double getPrice() {
		return price;
	}

	public String getImgUrl() {
		return imgUrl;
	}


	
	
	
}
