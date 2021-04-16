package com.microservices.payment.entity;

import com.microservices.payment.data.dto.SaleDTO;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.Lazy;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "sale")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Sale implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @DateTimeFormat(pattern = "yyyy/MM/dd")
    @Column(name = "dateOfTheSale",nullable = false)
    private Date dateOfTheSale;

    @Column(name = "amount",nullable = false,length = 10)
    private Double amount;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "sale", cascade = CascadeType.REFRESH)
    private List<ProductSale>products;

    public static Sale create(SaleDTO saleDTO) {
        return new ModelMapper().map(saleDTO, Sale.class);
    }
}
