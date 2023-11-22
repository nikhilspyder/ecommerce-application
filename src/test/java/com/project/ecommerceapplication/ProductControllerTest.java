package com.project.ecommerceapplication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.ecommerceapplication.controller.ProductController;
import com.project.ecommerceapplication.resource.ECommerceCategory;
import com.project.ecommerceapplication.resource.ProductResource;
import com.project.ecommerceapplication.resource.ProductResources;
import com.project.ecommerceapplication.service.ProductService;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.*;
import static org.hamcrest.Matchers.hasSize;


@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProductService productService;

    @Test
    public void testCreateProduct() throws Exception {
        // Create a ProductResource for testing
        ProductResource productResource = new ProductResource();
        productResource.setId(1L);
        productResource.setName("Batman");
        productResource.setDescription("Description1");
        productResource.setCategory(ECommerceCategory.BOOKS);
        productResource.setPrice(10.00);
        productResource.setStock(5);

        // Convert the ProductResource to JSON
        String productJson = objectMapper.writeValueAsString(productResource);

        // Mock the productService.createProduct method
        when(productService.createProduct(any(ProductResource.class))).thenReturn(productResource);
        
        ArgumentCaptor<ProductResource> argumentCaptor = ArgumentCaptor.forClass(ProductResource.class);

        // Perform the POST request
        mockMvc.perform(post("/createProduct")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productJson))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Batman"))
                .andExpect(jsonPath("$.description").value("Description1"))
                .andExpect(jsonPath("$.category").value(ECommerceCategory.BOOKS.name()))
                .andExpect(jsonPath("$.price").value(10.00))
                .andExpect(jsonPath("$.stock").value(5));
        
     // Verify that productService.createProduct was called with the expected argument
        verify(productService).createProduct(argumentCaptor.capture());

        // Print the actual argument for debugging
        System.out.println("Actual Argument: " + argumentCaptor.getValue());
        
    }
    
    @Test
    public void testGetAllProducts() throws Exception {
        // Mock data for testing
        ProductResource product1 = new ProductResource();
        product1.setId(1L);
        product1.setName("Batman");
        product1.setDescription("Description1");
        product1.setCategory(ECommerceCategory.BOOKS);
        product1.setPrice(10.00);
        product1.setStock(5);
        
        ProductResource product2 = new ProductResource();
        product2.setId(2L);
        product2.setName("Superman");
        product2.setDescription("Description2");
        product2.setCategory(ECommerceCategory.BOOKS);
        product2.setPrice(10.00);
        product2.setStock(5);

        List<ProductResource> productResourceList = new ArrayList<>();
        
        ProductResources productResources = new ProductResources();
        productResourceList.add(product1);
        productResourceList.add(product2);
        productResources.setProductResourceList(productResourceList);

        // Mock the productService.getAllProducts method
        when(productService.getAllProducts()).thenReturn(productResources);

        // Perform the GET request
        mockMvc.perform(get("/getAllProducts")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.productResourceList", hasSize(2)))
                .andExpect(jsonPath("$.productResourceList[0].id").value(1L))
                .andExpect(jsonPath("$.productResourceList[0].name").value("Batman"))
                .andExpect(jsonPath("$.productResourceList[0].description").value("Description1"))
                .andExpect(jsonPath("$.productResourceList[0].category").value(ECommerceCategory.BOOKS.name()))
                .andExpect(jsonPath("$.productResourceList[0].price").value(10.00))
                .andExpect(jsonPath("$.productResourceList[0].stock").value(5))
		        .andExpect(jsonPath("$.productResourceList[1].id").value(2L))
		        .andExpect(jsonPath("$.productResourceList[1].name").value("Superman"))
		        .andExpect(jsonPath("$.productResourceList[1].description").value("Description2"))
		        .andExpect(jsonPath("$.productResourceList[1].category").value(ECommerceCategory.BOOKS.name()))
		        .andExpect(jsonPath("$.productResourceList[1].price").value(10.00))
		        .andExpect(jsonPath("$.productResourceList[1].stock").value(5));

        // Verify that productService.getAllProducts was called
        verify(productService).getAllProducts();
    }
    
}
