package com.microservices.payment.data.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.microservices.payment.entity.ProductSale;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;


import java.io.Serializable;

@JsonPropertyOrder({"id","idProduct","quantity"})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
public class ProductSaleDTO extends RepresentationModel<ProductSaleDTO> implements Serializable {


    @JsonProperty("id")
    private Long id;

    @JsonProperty("idProduct")
    private Long idProduct;

    @JsonProperty("quantity")
    private Integer quantity;


    public static ProductSaleDTO create(ProductSale productSale){
        return new ModelMapper().map(productSale,ProductSaleDTO.class);
    }

}
