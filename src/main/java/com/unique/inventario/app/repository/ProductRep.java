package com.unique.inventario.app.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.unique.inventario.app.models.Product;

public interface ProductRep extends ReactiveMongoRepository<Product, String>{

}
