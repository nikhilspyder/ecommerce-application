package com.project.ecommerceapplication.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.project.ecommerceapplication.entity.ProductEntity;
import com.project.ecommerceapplication.mapper.CustomerMapper;
import com.project.ecommerceapplication.mapper.ProductMapper;
import com.project.ecommerceapplication.resource.ProductResource;
import com.project.ecommerceapplication.resource.ProductResources;
import com.project.ecommerceapplication.respository.CustomerRepository;
import com.project.ecommerceapplication.respository.ProductRepository;
import com.project.ecommerceapplication.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	
	private static final Logger LOGGER = LogManager.getLogger(ProductServiceImpl.class);

    private final ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);
    
    @Autowired
    private ProductRepository productRepository;

	@Override
	public ProductResource createProduct(ProductResource productResource) {
		
		LOGGER.info("Inside ProductServiceImpl - createProduct");
		
		try {
			
			ProductEntity productEntity = productMapper.mapResourceToEntity(productResource);
			
			ProductEntity response = productRepository.save(productEntity);
			
			return productMapper.mapEntityToResource(response);
			
			
		}catch(Exception e) {
			LOGGER.error("Error while creating product: " + e.getMessage(), e);
            throw e;
		}
		
	}

	@Override
	public ProductResources getAllProducts() {
		
		LOGGER.info("Inside ProductServiceImpl - getAllProducts");
		
		try {
			
			List<ProductEntity> productEntityList = (List<ProductEntity>) productRepository.findAll();
			
			List<ProductResource> productResourceList = new ArrayList<>();
			ProductResources productResources = new ProductResources();
			
			for(ProductEntity productEntity : productEntityList) {
				ProductResource resource = productMapper.mapEntityToResource(productEntity);
				productResourceList.add(resource);
			}
			
			productResources.setProductResourceList(productResourceList);
			
			return productResources;
			
		}catch(Exception e) {
			LOGGER.error("Error while getAllProducts : " + e.getMessage(), e);
            throw e;
		}
	}

	@Override
	public ProductResources getAllProductsByCategory(String category) {

		LOGGER.info("Inside ProductServiceImpl - getAllProductsByCategory");
		
		try {
			
			Optional<List<ProductEntity>> response = productRepository.findProductsByCategory(category);
			ProductResources productResources = new ProductResources();
			
			if(response.isPresent()) {
				List<ProductResource> productResourceList = new ArrayList<>();
				
				List<ProductEntity> productList = (List<ProductEntity>) response.get();
				
				if(!CollectionUtils.isEmpty(productList)) {
					for(ProductEntity productEntity : productList) {
						ProductResource resource = productMapper.mapEntityToResource(productEntity);
						productResourceList.add(resource);
					}
					
					productResources.setProductResourceList(productResourceList);
				}else {
					LOGGER.info("No Products found for category - " + category);
				}
			}
			return productResources;
			
		}catch(Exception e) {
			LOGGER.error("Error while getAllProducts : " + e.getMessage(), e);
            throw e;
		}
	}
	
	@Override
	public ProductResource getProductByProductId(Long productId) {

		LOGGER.info("Inside ProductServiceImpl - getProductByProductId");
		
		try {
			
			Optional<ProductEntity> response = productRepository.findByProductId(productId);
			ProductResource productResource = new ProductResource();
			
			if(response.isPresent()) {
				
				ProductEntity productEntity = response.get();
					
				if(Objects.nonNull(productEntity)) {
					productResource = productMapper.mapEntityToResource(productEntity);
					LOGGER.info("Product found for productId - " + productId);
					LOGGER.info("response - " + productResource);
					
				}else {
					LOGGER.info("No Product found for productId - " + productId);
				}
	
				return productResource;
			}
		}catch(Exception e) {
			LOGGER.error("Error while getProductByProductId : " + e.getMessage(), e);
            throw e;
		}
		return null;
	}
	
	@Override
	public ProductResource updateProduct(ProductResource productResource){
		
		LOGGER.info("Inside ProductServiceImpl - updateProduct");
		
		try {
			
			Optional<ProductEntity> response = productRepository.findById(productResource.getId());
			ProductResource productResponse = null;
			
			if(response.isPresent()) {
				
				ProductEntity productEntity = response.get();
				
				if(Objects.nonNull(productEntity)) {
					
					ProductEntity resp = productRepository.save(productMapper.mapResourceToEntity(productResource));
					productResponse = productMapper.mapEntityToResource(resp);
				}else {
					LOGGER.info("No Product found for product id - " + productResource.getId());
				}
			}
			return productResponse;
			
		}catch(Exception e) {
			LOGGER.error("Error while updateProduct : " + e.getMessage(), e);
            throw e;
		}
	}

	@Override
	public boolean deleteProduct(Long id) {
		
		LOGGER.info("Inside ProductServiceImpl - deleteProduct");
		
		try {
            if (productRepository.existsById(id)) {
                productRepository.deleteById(id);
                return true;
            } else {
            	LOGGER.info("No Product found for product id - " + id);
                return false;
            }
        } catch (Exception e) {
        	LOGGER.error("Error while deleteProduct : " + e.getMessage(), e);
            return false; 
        }
	}
	
	
	

}
