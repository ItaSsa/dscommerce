package com.devsuperior.dscommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dscommerce.config.SecurityConfig;
import com.devsuperior.dscommerce.dtos.UserDTO;
import com.devsuperior.dscommerce.entities.Role;
import com.devsuperior.dscommerce.entities.User;
import com.devsuperior.dscommerce.projections.UserDetailsProjection;
import com.devsuperior.dscommerce.repositories.UserRepository;
import com.devsuperior.dscommerce.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService implements UserDetailsService{

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	private SecurityConfig securityConfig;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		List<UserDetailsProjection> listRole = userRepository.searchUserAndRolesByEmail(username);
		if(listRole.size()==0) {
			throw new UsernameNotFoundException("User not found");
		}
		
		User user = new User();
		user.setEmail(username);
		user.setPassword(listRole.get(0).getPassword());
		for(UserDetailsProjection projection: listRole) {
			user.addRole(new Role(projection.getRoleId(),projection.getAuthority()));
		}
		
		
		return user;
	}
	
	@Transactional
	public UserDTO updatePassword(Long id) {
		try {
			User entity = userRepository.getReferenceById(id);
			entity.setPassword(securityConfig.getPasswordEncoder().encode(entity.getPassword()));
			entity = userRepository.save(entity);
			return new UserDTO(entity);
		}catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(" User not found ");
		}
		
	}

	
}
