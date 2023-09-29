package com.devsuperior.dscommerce.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException.Forbidden;

import com.devsuperior.dscommerce.dtos.CategoryDTO;
import com.devsuperior.dscommerce.dtos.ProductDTO;
import com.devsuperior.dscommerce.dtos.ProductMinDTO;
import com.devsuperior.dscommerce.entities.Category;
import com.devsuperior.dscommerce.entities.Product;
import com.devsuperior.dscommerce.repositories.ProductRepository;
import com.devsuperior.dscommerce.services.exceptions.DatabaseException;
import com.devsuperior.dscommerce.services.exceptions.ForbidenException;
import com.devsuperior.dscommerce.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Component
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	//To avoid databases lock
	@Transactional(readOnly = true)
	public ProductDTO findById(Long id) {
		Optional<Product> result = productRepository.findById(id);
		Product product = result.orElseThrow(() -> new ResourceNotFoundException("Id not found"));
		ProductDTO dto = new ProductDTO(product);
		return dto;
	}
	
	
	@Transactional(readOnly = true)
	public Page<ProductMinDTO> searchByName(String name, Pageable pageable) {
		Page<Product> result = productRepository.searchByName(name,pageable);
		return result.map(x -> new ProductMinDTO(x));
	}

	@Transactional
	public ProductDTO insert(ProductDTO dto) {
	
			Product entity = new Product();
			copyDtoToEntity(dto, entity);
			productRepository.save(entity);
			return new ProductDTO(entity);


	}
	
	
	@Transactional
	public ProductDTO update(Long id, ProductDTO dto) {
		try {
			Product entity = productRepository.getReferenceById(id);
			copyDtoToEntity(dto, entity);
			entity = productRepository.save(entity);
			return new ProductDTO(entity);
		}catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(" Product not found ");
		}

	}
	
	
	@Transactional(propagation = Propagation.SUPPORTS)
	public void delete (Long id) {
		
		if (!productRepository.existsById(id)) {
			throw new ResourceNotFoundException("Product not found");
		}
		try {
			productRepository.deleteById(id);    		
		}catch(DataIntegrityViolationException e ) {
			throw new DatabaseException("Referential integrity constraint violation - There are orders referencing this product");
		}

	}
	
	private void copyDtoToEntity(ProductDTO dto, Product entity) {
			entity.setName(dto.getName()) ;
			entity.setDescription(dto.getDescription()) ;
			entity.setPrice(dto.getPrice());
			entity.setImgUrl(dto.getImgUrl()) ;
			
			entity.getCategories().clear();
			for(CategoryDTO catDto: dto.getCategories()) {
				Category cat = new Category();
				cat.setId(catDto.getId());
				entity.getCategories().add(cat);
			}

	}

}
