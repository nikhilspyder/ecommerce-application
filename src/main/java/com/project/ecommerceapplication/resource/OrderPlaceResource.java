package com.project.ecommerceapplication.resource;

public class OrderPlaceResource {
	
	private Long productId;
	
	private Long customerId;
	
	private int productQuantity;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public int getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}

	@Override
	public String toString() {
		return "OrderPlaceResource [productId=" + productId + ", customerId=" + customerId + ", productQuantity="
				+ productQuantity + "]";
	}
	
}
