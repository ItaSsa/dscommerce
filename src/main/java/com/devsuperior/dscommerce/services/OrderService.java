package com.devsuperior.dscommerce.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dscommerce.dtos.OrderDTO;
import com.devsuperior.dscommerce.entities.Order;
import com.devsuperior.dscommerce.repositories.OrderRepository;
import com.devsuperior.dscommerce.services.exceptions.ResourceNotFoundException;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository repository;
	
	//To avoid databases lock
	@Transactional(readOnly = true)
	public OrderDTO findById(Long id) {
		Optional<Order> result = repository.findById(id);
		Order order = result.orElseThrow(() -> new ResourceNotFoundException("Id not found"));
		OrderDTO dto = new OrderDTO(order);
		return dto;
	}
	
}
