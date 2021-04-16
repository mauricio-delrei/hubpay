package com.microservices.payment.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.microservices.payment.entity.Sale;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;


import java.io.Serializable;
import java.util.Date;
import java.util.List;


@JsonPropertyOrder({"id","dateOfTheSale","amount","products"})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
public class SaleDTO extends RepresentationModel<SaleDTO>implements Serializable {

    @JsonProperty("id")
    private Long id;


    @JsonProperty("dateOfTheSale")
    private Date dateOfTheSale;


    @JsonProperty("amount")
    private Double amount;


    @JsonProperty("products")
    private List<ProductSaleDTO> products;

    public static SaleDTO create(Sale sale){
        return new ModelMapper().map(sale,SaleDTO.class);
    }



}
