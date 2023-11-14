package com.project.ecommerceapplication.entity;

import javax.persistence.*;
import javax.transaction.Transactional;

import com.project.ecommerceapplication.resource.ECommerceCategory;

@Entity
@Transactional
@Table(name = "Product")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private Double price;

    private int stock;

    @Enumerated(EnumType.STRING)
    private ECommerceCategory category;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public ECommerceCategory getCategory() {
		return category;
	}

	public void setCategory(ECommerceCategory category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "ProductEntity [id=" + id + ", name=" + name + ", description=" + description + ", price=" + price
				+ ", stock=" + stock + ", category=" + category + "]";
	}
    
}
