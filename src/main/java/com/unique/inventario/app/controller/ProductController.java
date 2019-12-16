package com.unique.inventario.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.unique.inventario.app.models.Product;
import com.unique.inventario.app.services.ProductService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/product")
public class ProductController {

	@Autowired
	ProductService service;
	
	@GetMapping
	public Flux<Product> findAll(){
		return service.findAll();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<Product> create(@RequestBody Product product){
		return service.create(product);
	}
	
	@GetMapping("/{id}")
	public Mono<Product> findById(@PathVariable String id){
		return service.findById(id)
				.defaultIfEmpty(new Product());
	}
	
	@DeleteMapping("/{id}")
	public Mono<Void> delete(@PathVariable String id){
		return service.delete(id);
	}
	
	@PutMapping("/{id}")
	public Mono<Product> update(@RequestBody Product product, @PathVariable String id){
		return service.update(product, id);
	}
	
	@PutMapping("/addStock/{id}/{quantity}")
	public Mono<Product> addStock(@PathVariable String id, @PathVariable Integer quantity){
		return service.addStock(id, quantity);
	}
	
	@PutMapping("/removeStock/{id}/{quantity}")
	public Mono<Product> removeStock(@PathVariable String id, @PathVariable Integer quantity){
		return service.findById(id).filter(p->p.getQuantity()>=quantity)
				.flatMap(p->service.removeStock(p.getId(), quantity))
				.defaultIfEmpty(new Product());
				
	}
}
