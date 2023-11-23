package com.project.ecommerceapplication.resource;

import java.util.Date;
import java.util.UUID;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.project.ecommerceapplication.entity.CustomerRegisterEntity;
import com.project.ecommerceapplication.entity.ProductEntity;

public class OrderResource {
	
	private Long id;

	private Long orderId;
	
	private int productQuantity;
	
	private double productPrice;
	
	private double subTotal;
	
	private Date purchaseDate;
	
	private Date shippingDate;
	
	private boolean isDelivered;
	
	private ProductResource productId;

    private CustomerRegisterResource customerId;
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public int getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	public double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public Date getShippingDate() {
		return shippingDate;
	}

	public void setShippingDate(Date shippingDate) {
		this.shippingDate = shippingDate;
	}

	public boolean isDelivered() {
		return isDelivered;
	}

	public void setDelivered(boolean isDelivered) {
		this.isDelivered = isDelivered;
	}
	
	public ProductResource getProductId() {
		return productId;
	}

	public void setProductId(ProductResource productId) {
		this.productId = productId;
	}

	public CustomerRegisterResource getCustomerId() {
		return customerId;
	}

	public void setCustomerId(CustomerRegisterResource customerId) {
		this.customerId = customerId;
	}

	@Override
	public String toString() {
		return "OrderResource [id=" + id + ", orderId=" + orderId + ", productQuantity=" + productQuantity
				+ ", productPrice=" + productPrice + ", subTotal=" + subTotal + ", purchaseDate=" + purchaseDate
				+ ", shippingDate=" + shippingDate + ", isDelivered=" + isDelivered + ", productId=" + productId
				+ ", customerId=" + customerId + "]";
	}

}
