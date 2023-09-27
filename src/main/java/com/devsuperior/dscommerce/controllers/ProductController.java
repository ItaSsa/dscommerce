	package com.devsuperior.dscommerce.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.devsuperior.dscommerce.dtos.ProductDto;
import com.devsuperior.dscommerce.services.ProductService;

import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;

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
	public ResponseEntity<Page<ProductDto>> findAll(
				@RequestParam(name="name", defaultValue = "") String name,			
				Pageable pageable) {
		return ResponseEntity.ok(productService.searchByName(name,pageable));
		
	}
	
	
	@PostMapping
	public ResponseEntity<ProductDto> insert(@Valid @RequestBody  ProductDto dto) {
		dto = productService.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				  .buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
				
		
	}

	@PutMapping(value ="/{id}")
	public ResponseEntity<ProductDto> update(@PathVariable Long id,
												@Valid 	@RequestBody  ProductDto dto) {
		dto = productService.update(id,dto);
		return ResponseEntity.ok(dto);
		
	}
	
	@DeleteMapping(value ="/{id}")
	public ResponseEntity<ProductDto> delete(@PathVariable Long id) {
		productService.delete(id);
		return ResponseEntity.noContent().build();
		
	}
}
