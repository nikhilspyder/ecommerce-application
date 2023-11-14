package com.project.ecommerceapplication.service;

import com.project.ecommerceapplication.resource.ProductResource;
import com.project.ecommerceapplication.resource.ProductResources;

public interface ProductService {
	
	public ProductResource createProduct(ProductResource productResource);
	
	public ProductResources getAllProducts();

	public ProductResources getAllProductsByCategory(String category);
	
	public ProductResource updateProduct(ProductResource productResource);

	public boolean deleteProduct(Long id);
	
	public ProductResource getProductByProductId(Long productId);
	
}
