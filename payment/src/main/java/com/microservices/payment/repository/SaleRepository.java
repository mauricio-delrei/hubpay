package com.microservices.payment.repository;

import com.microservices.payment.entity.Sale;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SaleRepository extends PagingAndSortingRepository<Sale, Long> {

}
