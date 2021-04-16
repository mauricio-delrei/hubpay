package com.microservices.payment.entity;

import com.microservices.payment.data.dto.ProductSaleDTO;
import lombok.*;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "product_sale")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ProductSale implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "id_product",nullable = false, length = 10)
    private Long idProduct;

    @Column(name = "quantity",nullable = false, length = 10)
    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sale")
    private Sale sale;

    public static ProductSale create(ProductSaleDTO productSaleDTO){
        return new ModelMapper().map(productSaleDTO, ProductSale.class);
    }



}
