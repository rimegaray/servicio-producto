package com.unique.inventario.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unique.inventario.app.models.Product;
import com.unique.inventario.app.repository.ProductRep;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductServiceImpl implements ProductService {

	
	@Autowired
	ProductRep repository;
	
	@Override
	public Mono<Product> findById(String id) {
		return repository.findById(id);
	}

	@Override
	public Mono<Void> delete(String id) {
		return repository.findById(id).flatMap(product -> repository.delete(product));
	}

	@Override
	public Flux<Product> findAll() {
		return repository.findAll();
	}

	@Override
	public Mono<Product> update(Product product, String id) {
		return repository.findById(id).flatMap(p -> {
			p.setName(product.getName());
			p.setPrice(product.getPrice());
			p.setCategory(product.getCategory());
			return repository.save(p);
		});
	}

	@Override
	public Mono<Product> create(Product product) {
		if(product.getQuantity()== null) {
			product.setQuantity(0);
		}
		return repository.save(product);
	}

	@Override
	public Mono<Product> addStock(String id, Integer quantity) {
		return repository.findById(id).flatMap(p ->{
			p.setQuantity(p.getQuantity()+quantity);
			return repository.save(p);
		});
	}

	@Override
	public Mono<Product> removeStock(String id, Integer quantity) {
		return repository.findById(id).flatMap(p ->{
			p.setQuantity(p.getQuantity()-quantity);
			return repository.save(p);
		});
	}

}
