package com.microservices.crud.service;

import java.util.Optional;

import com.microservices.crud.message.ProductSendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.microservices.crud.data.dto.ProductDTO;
import com.microservices.crud.entity.Product;
import com.microservices.crud.exception.ResourceNotFoundException;
import com.microservices.crud.repository.ProductRepository;

@Service
public class ProductService {

	private final ProductRepository productRepository;
	private final ProductSendMessage productSendMessage;

	@Autowired
	public ProductService(ProductRepository productRepository,
						  ProductSendMessage productSendMessage) {
		this.productRepository = productRepository;
		this.productSendMessage = productSendMessage;
	}

	public ProductDTO create(ProductDTO productDto) {
		ProductDTO prodReturn = ProductDTO.create(productRepository.save(Product.create(productDto)));
		productSendMessage.sendMessage(prodReturn);
		return prodReturn;
	}

	public Page<ProductDTO> findAll(Pageable pageable) {
		var page = productRepository.findAll(pageable);
		return page.map(this::covertToProdutoDTO);
	}

	public ProductDTO findById(Long id) {
		var entity = productRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found this id"));
		return ProductDTO.create(entity);

	}

	public ProductDTO update(ProductDTO productDTO) {
		final Optional<Product> optionalProduct = productRepository.findById(productDTO.getId());

		if (!optionalProduct.isPresent()) {
			new ResourceNotFoundException("No records found this id");
		}
		return ProductDTO.create(productRepository.save(Product.create(productDTO)));
	}

	public void delete(Long id) {
		var entity = productRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found this id"));
		productRepository.delete(entity);

	}

	private ProductDTO covertToProdutoDTO(Product product) {
		return ProductDTO.create(product);
	}

}
