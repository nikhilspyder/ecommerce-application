package com.project.ecommerceapplication.resource;

import java.util.List;

public class ProductResources {
	
	private List<ProductResource> productResourceList;

	public List<ProductResource> getProductResourceList() {
		return productResourceList;
	}

	public void setProductResourceList(List<ProductResource> productResourceList) {
		this.productResourceList = productResourceList;
	}

	@Override
	public String toString() {
		return "ProductResources [productResourceList=" + productResourceList + "]";
	}

}
