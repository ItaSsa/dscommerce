package com.devsuperior.dscommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dscommerce.dtos.CategoryDTO;
import com.devsuperior.dscommerce.entities.Category;
import com.devsuperior.dscommerce.repositories.CategoryRepository;

@Component
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	//To avoid databases lock
	@Transactional(readOnly = true)
	public List<CategoryDTO> findAll() {
		List<Category> result = categoryRepository.findAll();
		List<CategoryDTO> categories = result.stream().map(x -> new CategoryDTO(x)).toList();
		return categories;
	}
	
	
}
