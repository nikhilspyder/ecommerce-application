package com.project.ecommerceapplication.controller;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.ecommerceapplication.resource.CustomerLoginResource;
import com.project.ecommerceapplication.resource.CustomerRegisterResource;
import com.project.ecommerceapplication.resource.ECommerceCategory;
import com.project.ecommerceapplication.resource.ProductResource;
import com.project.ecommerceapplication.resource.ProductResources;
import com.project.ecommerceapplication.service.ProductService;

@RestController
@RequestMapping("/")
public class ProductController {
	private static final Logger LOGGER = LogManager.getLogger(ProductController.class);
    Counter createProductCounter;
    Counter getAllProducts;
    Counter updateProduct;
    Counter deleteProduct;
    public ProductController(MeterRegistry registry) {
        createProductCounter = Counter.builder("create_product_counter")
                .description("Number of products created")
                .register(registry);
        getAllProducts = Counter.builder("get_all_products_counter")
                .description("Number of getAllProducts requests")
                .register(registry);
        updateProduct = Counter.builder("update_product_counter")
                .description("Number of update product requests")
                .register(registry);
        deleteProduct = Counter.builder("delete_product_counter")
                .description("Number of delete product requests")
                .register(registry);
    }
	@Autowired
	private ProductService productService;

	
	@PostMapping("/createProduct")
    public ResponseEntity<?> createProduct(@Valid @RequestBody ProductResource productResource) {

        LOGGER.info("Inside ProductController - createProduct");
        createProductCounter.increment();
        try{
        	ProductResource response = productService.createProduct(productResource);
            LOGGER.info("Executed ProductController - createProduct");
            LOGGER.info("response - " + response);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }catch(Exception e){
            LOGGER.error("Caught Exception - "+ e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
	
	@GetMapping("/getAllProducts")
	public ResponseEntity<?> getAllProducts(@RequestParam(required = false) String category){
		
		LOGGER.info("Inside ProductController - getAllProducts");
		ProductResources response;
        getAllProducts.increment();
        try{
        	
        	if(StringUtils.hasText(category)) {
        		
        		response = productService.getAllProductsByCategory(category);
        		LOGGER.info("Executed ProductController - getAllProducts by category - " + category);
        		
        	}else {
        		response = productService.getAllProducts();
        		LOGGER.info("Executed ProductController - getAllProducts");
        	}
        	
            
            LOGGER.info("response - " + response);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }catch(Exception e){
            LOGGER.error("Caught Exception - "+ e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
		
	}
	
	@GetMapping("/getAllCategories")
    public ResponseEntity<?> getAllCategories() {
		
		LOGGER.info("Inside ProductController - getAllCategories");

        try{
        	List<String> response = productService.getAllCategories();
            LOGGER.info("Executed ProductController - getAllCategories");
            LOGGER.info("response - " + response);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }catch(Exception e){
            LOGGER.error("Caught Exception - "+ e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
		
    }
	
	@PutMapping("/updateProduct")
    public ResponseEntity<?> updateProduct(@Valid @RequestBody ProductResource productResource) {
        updateProduct.increment();
        LOGGER.info("Inside ProductController - updateProduct");

        try{
        	ProductResource response = productService.updateProduct(productResource);
            LOGGER.info("Executed ProductController - updateProduct");
            LOGGER.info("response - " + response);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }catch(Exception e){
            LOGGER.error("Caught Exception - "+ e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
	
	@DeleteMapping("/deleteProduct")
    public ResponseEntity<?> deleteProduct(@RequestParam Long id) {
        deleteProduct.increment();
        LOGGER.info("Inside ProductController - deleteProduct");

        try{
        	Boolean val = productService.deleteProduct(id);
            LOGGER.info("Executed ProductController - deleteProduct");
            LOGGER.info("response - " + val);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(val);
        }catch(Exception e){
            LOGGER.error("Caught Exception - "+ e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
	
}
