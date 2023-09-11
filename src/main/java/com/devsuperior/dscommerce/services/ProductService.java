package com.devsuperior.dscommerce.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dscommerce.dtos.ProductDto;
import com.devsuperior.dscommerce.entities.Product;
import com.devsuperior.dscommerce.repositories.ProductRepository;

@Component
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	//To avoid databases lock
	@Transactional(readOnly = true)
	public ProductDto findById(Long id) {
		Optional<Product> result = productRepository.findById(id);
		Product product = result.get();
		ProductDto dto = new ProductDto(product);
		return dto;
	}
	
	
	@Transactional(readOnly = true)
	public Page<ProductDto> findAll(Pageable pageable) {
		Page<Product> result = productRepository.findAll(pageable);
		return result.map(x -> new ProductDto(x));
	}

	@Transactional
	public ProductDto insert(ProductDto dto) {
		
		Product product = new Product(dto);
		
		productRepository.save(product);
		
		return new ProductDto(product);
	}

}
