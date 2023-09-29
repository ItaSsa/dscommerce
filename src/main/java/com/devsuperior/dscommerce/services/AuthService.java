package com.devsuperior.dscommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.dscommerce.entities.User;
import com.devsuperior.dscommerce.services.exceptions.ForbidenException;

@Service
public class AuthService {

	@Autowired
	UserService userService;
	
	public void validateSelfOrAdmin(Long userId) {
		User me =  userService.authenticated();
		if(!me.hasRole("ROLE_ADMIN") && !me.getId().equals(userId)) {
			throw new ForbidenException("Access denied");
		}
	}
}
