package com.microservices.payment.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.microservices.payment.entity.Product;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;
import java.io.Serializable;


@JsonPropertyOrder({"id","inventory"})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
public class ProductDTO extends RepresentationModel<ProductDTO> implements Serializable {


    @JsonProperty("id")
    private Long id;

    @JsonProperty("inventory")
    private Integer inventory;

    public static ProductDTO create(Product product){
        return new ModelMapper().map(product,ProductDTO.class);
    }
}
