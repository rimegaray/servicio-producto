package com.unique.inventario.app.services;

import com.unique.inventario.app.models.Product;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {

	public Mono<Product> findById(String id);
	
	public Mono<Void> delete(String id);
	
	public Flux<Product> findAll();
	
	public Mono<Product> update(Product product, String id);
	
	public Mono<Product> create(Product product);
	
	public Mono<Product> addStock(String id, Integer quantity);
	
	public Mono<Product> removeStock(String id, Integer quantity);
}
