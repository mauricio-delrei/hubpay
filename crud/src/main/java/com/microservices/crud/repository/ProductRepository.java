package com.microservices.crud.repository;


import org.springframework.data.repository.PagingAndSortingRepository;

import com.microservices.crud.entity.Product;

public interface ProductRepository extends PagingAndSortingRepository<Product, Long>{

	

}
