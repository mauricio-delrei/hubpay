package com.microservices.payment.repository;

import com.microservices.payment.entity.ProductSale;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductSaleRepository extends PagingAndSortingRepository<ProductSale,Long> {
}
