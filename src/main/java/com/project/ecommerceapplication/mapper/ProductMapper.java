package com.project.ecommerceapplication.mapper;

import org.mapstruct.Mapper;

import com.project.ecommerceapplication.entity.ProductEntity;
import com.project.ecommerceapplication.resource.ProductResource;

@Mapper
public interface ProductMapper {
	
	ProductEntity mapResourceToEntity(ProductResource productResource);

	ProductResource mapEntityToResource(ProductEntity productEntity);
}

