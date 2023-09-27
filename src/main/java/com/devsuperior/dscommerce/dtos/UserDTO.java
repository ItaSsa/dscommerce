package com.devsuperior.dscommerce.dtos;

import com.devsuperior.dscommerce.entities.User;

public class UserDTO {
	private Long id;
	private String name;
	private String email;
	
	public UserDTO() {
		// TODO Auto-generated constructor stub
	}

	public UserDTO(Long id, String name, String email) {
		this.id = id;
		this.name = name;
		this.email = email;
	}

	public UserDTO(User entity) {
		id = entity.getId();
		name = entity.getName();
		email = entity.getEmail();
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}


}
