package br.com.cotiinformatica.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

import br.com.cotiinformatica.dtos.ProductRequestDto;
import br.com.cotiinformatica.dtos.ProductResponseDto;
import br.com.cotiinformatica.services.ProductDomainService;

@RestController
@RequestMapping(value = "/api/products")
public class ProductController {
	
	@Autowired ProductDomainService productDomainService;
	
	@PostMapping
	public ResponseEntity<ProductResponseDto> post(@RequestBody ProductRequestDto request) throws Exception {
		return ResponseEntity.status(201).body(productDomainService.create(request));
	}
	
	@PutMapping("{id}")
	public ResponseEntity<ProductResponseDto> put(@PathVariable("id") Long productId, 
			@RequestBody ProductRequestDto request) throws Exception {
		return ResponseEntity.ok().body(productDomainService.update(productId, request));
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<ProductResponseDto> delete(@PathVariable("id") Long productId) throws Exception {
		return ResponseEntity.ok().body(productDomainService.delete(productId));
	}
	
	@GetMapping("{id}")
	public ResponseEntity<ProductResponseDto> get(@PathVariable("id") Long productId) throws Exception {
		return ResponseEntity.ok().body(productDomainService.getById(productId));
	}
	
	@GetMapping
	public ResponseEntity<List<ProductResponseDto>> getAll (
			@RequestParam(defaultValue = "0") int page, 
			@RequestParam(defaultValue = "10") int size,
			@RequestParam(defaultValue = "id") String sortBy) throws Exception {
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
		return ResponseEntity.ok().body(productDomainService.getAll(pageable));
	}
}
