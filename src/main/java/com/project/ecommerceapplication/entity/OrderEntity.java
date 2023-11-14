package com.project.ecommerceapplication.entity;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.transaction.Transactional;

@Entity
@Transactional
@Table(name = "orders")
public class OrderEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long orderId;
	
//	private long productId;
	
	private int productQuantity;
	
	private double productPrice;
	
	private double subTotal;
	
	private Date purchaseDate;
	
	private Date shippingDate;
	
	private boolean isDelivered;
	
//	private Long customerId;
	
	@ManyToOne
    @JoinColumn(name = "productId", nullable = false)
    private ProductEntity productId;

    @ManyToOne
    @JoinColumn(name = "customerId", nullable = false)
    private CustomerRegisterEntity customerId;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

//	public long getProductId() {
//		return productId;
//	}
//
//	public void setProductId(long productId) {
//		this.productId = productId;
//	}

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
	
	public ProductEntity getProductId() {
		return productId;
	}

	public void setProductId(ProductEntity productId) {
		this.productId = productId;
	}

	public CustomerRegisterEntity getCustomerId() {
		return customerId;
	}

	public void setCustomerId(CustomerRegisterEntity customerId) {
		this.customerId = customerId;
	}

	@Override
	public String toString() {
		return "OrderEntity [orderId=" + orderId + ", productQuantity=" + productQuantity + ", productPrice="
				+ productPrice + ", subTotal=" + subTotal + ", purchaseDate=" + purchaseDate + ", shippingDate="
				+ shippingDate + ", isDelivered=" + isDelivered + ", productId=" + productId + ", customerId="
				+ customerId + "]";
	}


}
