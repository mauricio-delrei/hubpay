package com.microservices.payment.entity;


import com.microservices.payment.data.dto.ProductDTO;
import lombok.*;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Product implements Serializable {

    @Id
    private Long id;

    @Column(name = "inventory",nullable = false,length = 10)
    private Integer inventory;


    public static Product create(ProductDTO productDTO){
        return new ModelMapper().map(productDTO, Product.class);
    }
}
