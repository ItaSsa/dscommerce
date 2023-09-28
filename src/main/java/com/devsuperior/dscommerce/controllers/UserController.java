package com.devsuperior.dscommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dscommerce.dtos.UserDTO;
import com.devsuperior.dscommerce.services.UserService;

@RestController
@RequestMapping(value="/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PutMapping(value ="/{id}")
	public ResponseEntity<UserDTO> updatePassword(@PathVariable Long id) {
		UserDTO user = userService.updatePassword(id);
		return ResponseEntity.ok(user);
		
	}
	
	@GetMapping(value ="/me")
	public ResponseEntity<UserDTO> getMe() {
		UserDTO dto = userService.getMe();
		return ResponseEntity.ok(dto);
		
	}
}
