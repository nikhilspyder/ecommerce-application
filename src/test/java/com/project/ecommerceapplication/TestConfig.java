package com.project.ecommerceapplication;

import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import com.project.ecommerceapplication.service.CustomerService;
import com.project.ecommerceapplication.service.OrderService;
import com.project.ecommerceapplication.service.ProductService;

@TestConfiguration
public class TestConfig {

    @Bean
    @Primary
    public ProductService productService() {
        return Mockito.mock(ProductService.class);
    }
    
    @Bean
    @Primary
    public OrderService orderService() {
        return Mockito.mock(OrderService.class);
    }
    
    @Bean
    @Primary
    public CustomerService customerService() {
        return Mockito.mock(CustomerService.class);
    }
}
