package com.devsuperior.dscommerce.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.devsuperior.dscommerce.dtos.ProductDto;
import com.devsuperior.dscommerce.services.ProductService;

@RestController
@RequestMapping(value = "/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping(value ="/{id}")
	public ResponseEntity<ProductDto> findById(@PathVariable Long id) {
		ProductDto dto = productService.findById(id);
		return ResponseEntity.ok(dto);
		
	}
	
	@GetMapping
	public ResponseEntity<Page<ProductDto>> findAll(Pageable pageable) {
		return ResponseEntity.ok(productService.findAll(pageable));
		
	}
	
	
	
	@PostMapping
	public ResponseEntity<ProductDto> insert(@RequestBody  ProductDto dto) {
		dto = productService.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				  .buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
				
		
	}
}
