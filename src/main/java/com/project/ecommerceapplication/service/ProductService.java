package com.project.ecommerceapplication.service;

import com.project.ecommerceapplication.resource.ProductResource;
import com.project.ecommerceapplication.resource.ProductResources;

public interface ProductService {
	
	ProductResource createProduct(ProductResource productResource);
	
	ProductResources getAllProducts();

	ProductResources getAllProductsByCategory(String category);
	
	ProductResource updateProduct(ProductResource productResource);

	Boolean deleteProduct(Long id);
	
}
