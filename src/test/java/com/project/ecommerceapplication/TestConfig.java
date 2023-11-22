package com.project.ecommerceapplication;

import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import com.project.ecommerceapplication.service.ProductService;

@TestConfiguration
public class TestConfig {

    // If you have any additional beans or configurations for testing, define them here.

    // Example: Creating a mock bean for ProductService
    @Bean
    @Primary // This annotation is used to indicate that this bean should take precedence in case of conflicts.
    public ProductService productService() {
        return Mockito.mock(ProductService.class);
    }
}
