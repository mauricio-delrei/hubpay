package com.microservices.crud.data.dto;

import java.io.Serializable;

import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.microservices.crud.entity.Product;

@JsonPropertyOrder({"id","name","inventory","price"})
public class ProductDTO extends RepresentationModel<ProductDTO> implements Serializable{

	private static final long serialVersionUID = 1L;
	

	@JsonProperty("id")	
	private Long id;	

	@JsonProperty("name")
	private String name;	

	@JsonProperty("inventory")
	private Integer inventory;
	
	@JsonProperty("price")
	private Double price;
	
	
	public static ProductDTO create(Product product) {
		return new ModelMapper().map(product, ProductDTO.class);
	
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Integer getInventory() {
		return inventory;
	}


	public void setInventory(Integer inventory) {
		this.inventory = inventory;
	}


	public Double getPrice() {
		return price;
	}


	public void setPrice(Double price) {
		this.price = price;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductDTO other = (ProductDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}
