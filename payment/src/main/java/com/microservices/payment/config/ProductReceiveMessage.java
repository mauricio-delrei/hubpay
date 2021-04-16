package com.microservices.payment.config;

import com.microservices.payment.data.dto.ProductDTO;
import com.microservices.payment.entity.Product;
import com.microservices.payment.repository.ProductRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class ProductReceiveMessage {

    private final ProductRepository productRepository;

    @Autowired
    public ProductReceiveMessage(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @RabbitListener(queues = {"${crud.rabbitmq.queue}"})
    public void receive(@Payload ProductDTO productDTO){
        productRepository.save(Product.create(productDTO));
    }
}
