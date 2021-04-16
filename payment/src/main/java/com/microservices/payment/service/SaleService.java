package com.microservices.payment.service;

import com.microservices.payment.data.dto.SaleDTO;
import com.microservices.payment.entity.ProductSale;
import com.microservices.payment.entity.Sale;
import com.microservices.payment.exception.ResourceNotFoundException;
import com.microservices.payment.repository.ProductSaleRepository;
import com.microservices.payment.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SaleService {

    private final SaleRepository saleRepository;
    private final ProductSaleRepository productSaleRepository;

    @Autowired
    public SaleService(SaleRepository saleRepository, ProductSaleRepository productSaleRepository) {
        this.saleRepository = saleRepository;

        this.productSaleRepository = productSaleRepository;
    }


    public SaleDTO create(SaleDTO saleDTO) {
       Sale sale = saleRepository.save(Sale.create(saleDTO));
        List<ProductSale>productSaves = new ArrayList<>();
        saleDTO.getProducts().forEach(p -> {
            ProductSale ps = ProductSale.create(p);
            ps.setSale(sale);
            productSaves.add(productSaleRepository.save(ps));

        });
        sale.setProducts(productSaves);
        return SaleDTO.create(sale);
    }

    public Page<SaleDTO> findAll(Pageable pageable) {
        var page = saleRepository.findAll(pageable);
        return page.map(this::covertToSaleDTO);
    }


    private SaleDTO covertToSaleDTO(Sale sale) {
        return SaleDTO.create(sale);
    }

    public SaleDTO findById(Long id) {
        var entity = saleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found this id"));
        return SaleDTO.create(entity);

    }
}
