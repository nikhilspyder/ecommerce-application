package com.project.ecommerceapplication;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.ecommerceapplication.resource.CustomerRegisterResource;
import com.project.ecommerceapplication.resource.ECommerceCategory;
import com.project.ecommerceapplication.resource.OrderPlaceResource;
import com.project.ecommerceapplication.resource.OrderResource;
import com.project.ecommerceapplication.resource.ProductResource;
import com.project.ecommerceapplication.service.OrderService;

import static org.hamcrest.Matchers.hasSize;

@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private OrderService orderService;

    @Test
    public void testPlaceOrder() throws Exception {
        // Create a List of OrderPlaceResource for testing
        List<OrderPlaceResource> orderPlaceResourceList = new ArrayList<>();
        OrderPlaceResource order1 = new OrderPlaceResource();
        order1.setProductId(5L);
        order1.setProductQuantity(2);
        order1.setCustomerId(5L);

        OrderPlaceResource order2 = new OrderPlaceResource();
        order2.setProductId(2L);
        order2.setProductQuantity(1);
        order2.setCustomerId(5L);

        orderPlaceResourceList.add(order1);
        orderPlaceResourceList.add(order2);

        // Convert the List of OrderPlaceResource to JSON
        String orderJson = objectMapper.writeValueAsString(orderPlaceResourceList);

        // Mock the orderService.placeOrder method
        List<OrderResource> expectedOrderResponse = getExpectedOrderResponse(); // Implement this method to return the expected response
        when(orderService.placeOrder(anyList())).thenReturn(expectedOrderResponse);

        // Perform the POST request
        mockMvc.perform(post("/placeOrder")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(orderJson))
        .andExpect(status().isCreated())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$[0].orderId").value(13))
        .andExpect(jsonPath("$[0].productQuantity").value(2))
        .andExpect(jsonPath("$[0].productPrice").value(1000.99))
        .andExpect(jsonPath("$[0].subTotal").value(2001.98))
        .andExpect(jsonPath("$[0].delivered").value(true))
        .andExpect(jsonPath("$[0].purchaseDate").value(String.valueOf("2023-11-23T03:37:30.111+00:00")))
        .andExpect(jsonPath("$[0].shippingDate").value(String.valueOf("2023-11-23T03:37:30.111+00:00")))
        .andExpect(jsonPath("$[1].orderId").value(14))
        .andExpect(jsonPath("$[1].productQuantity").value(1))
        .andExpect(jsonPath("$[1].productPrice").value(79.99))
        .andExpect(jsonPath("$[1].subTotal").value(79.99))
        .andExpect(jsonPath("$[1].purchaseDate").value(String.valueOf("2023-11-23T03:37:30.111+00:00")))
        .andExpect(jsonPath("$[1].shippingDate").value(String.valueOf("2023-11-23T03:37:30.111+00:00")))
        .andExpect(jsonPath("$[1].delivered").value(true));

        // Verify that orderService.placeOrder was called with the expected argument
        verify(orderService).placeOrder(anyList());
    }

    private List<OrderResource> getExpectedOrderResponse() {
        List<OrderResource> expectedOrderResponse = new ArrayList<>();

        // Order 1
        OrderResource order1 = new OrderResource();
        order1.setOrderId(13L);
        order1.setProductQuantity(2);
        order1.setProductPrice(1000.99);
        order1.setSubTotal(2001.98);
        order1.setPurchaseDate(Date.from(Instant.parse("2023-11-23T03:37:30.111Z")));
        order1.setShippingDate(Date.from(Instant.parse("2023-11-23T03:37:30.111Z")));
        order1.setDelivered(true);

        ProductResource product1 = new ProductResource();
        product1.setId(5L);
        product1.setName("Apple MacBook Pro");
        product1.setDescription("High-performance laptop for all your computing needs.");
        product1.setPrice(1000.99);
        product1.setStock(10);
        product1.setCategory(ECommerceCategory.ELECTRONICS);

        order1.setProductId(product1);

        CustomerRegisterResource customer1 = new CustomerRegisterResource();
        customer1.setId(5L);
        customer1.setCustomerName("Mathhew");
        customer1.setCustomerEmail("Matthew@example.com");
        customer1.setFirstName("Mathhew");
        customer1.setLastName("Perry");
        customer1.setPassword("securepassword");
        customer1.setAddress("456 Main St");
        customer1.setPostcode(60616);
        customer1.setCity("Chicago");
        customer1.setPhone(1234567890);

        order1.setCustomerId(customer1);

        expectedOrderResponse.add(order1);

        // Order 2
        OrderResource order2 = new OrderResource();
        order2.setOrderId(14L);
        order2.setProductQuantity(1);
        order2.setProductPrice(79.99);
        order2.setSubTotal(79.99);
        order2.setPurchaseDate(Date.from(Instant.parse("2023-11-23T03:37:30.111Z")));
        order2.setShippingDate(Date.from(Instant.parse("2023-11-23T03:37:30.111Z")));
        order2.setDelivered(true);

        ProductResource product2 = new ProductResource();
        product2.setId(2L);
        product2.setName("Running Shoes 900");
        product2.setDescription("Comfortable running shoes for all types of runners.");
        product2.setPrice(79.99);
        product2.setStock(100);
        product2.setCategory(ECommerceCategory.SPORTS);

        order2.setProductId(product2);
        order2.setCustomerId(customer1);

        expectedOrderResponse.add(order2);

        return expectedOrderResponse;
    }
    
    
    @Test
    public void testGetAllOrders() throws Exception {
        // Mock data for testing
        OrderResource order1 = new OrderResource();
        order1.setOrderId(13L);
        order1.setProductQuantity(2);
        order1.setProductPrice(1000.99);
        order1.setSubTotal(2001.98);
        order1.setPurchaseDate(Date.from(Instant.parse("2023-11-23T03:37:30.111Z")));
        order1.setShippingDate(Date.from(Instant.parse("2023-11-23T03:37:30.111Z")));
        order1.setDelivered(true);

        // Set product details for order1
        ProductResource product1 = new ProductResource();
        product1.setId(5L);
        product1.setName("Apple MacBook Pro");
        order1.setProductId(product1);

        // Set customer details for order1
        CustomerRegisterResource customer1 = new CustomerRegisterResource();
        customer1.setId(5L);
        customer1.setCustomerName("Mathhew");
        order1.setCustomerId(customer1);

        OrderResource order2 = new OrderResource();
        order2.setOrderId(14L);
        order2.setProductQuantity(1);
        order2.setProductPrice(79.99);
        order2.setSubTotal(79.99);
        order2.setPurchaseDate(Date.from(Instant.parse("2023-11-23T03:37:30.111Z")));
        order2.setShippingDate(Date.from(Instant.parse("2023-11-23T03:37:30.111Z")));
        order2.setDelivered(true);

        // Set product details for order2
        ProductResource product2 = new ProductResource();
        product2.setId(2L);
        product2.setName("Running Shoes 900");
        order2.setProductId(product2);

        // Set customer details for order2
        CustomerRegisterResource customer2 = new CustomerRegisterResource();
        customer2.setId(5L);
        customer2.setCustomerName("Mathhew");
        order2.setCustomerId(customer2);

        List<OrderResource> orders = Arrays.asList(order1, order2);

        // Mock the orderService.getAllOrders method
        when(orderService.getAllOrders()).thenReturn(orders);

        // Perform the GET request
        mockMvc.perform(get("/getAllOrders")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].orderId").value(13))
                .andExpect(jsonPath("$[0].productQuantity").value(2))
                .andExpect(jsonPath("$[0].productPrice").value(1000.99))
                .andExpect(jsonPath("$[0].subTotal").value(2001.98))
                .andExpect(jsonPath("$[0].purchaseDate").value(String.valueOf("2023-11-23T03:37:30.111+00:00")))
                .andExpect(jsonPath("$[0].shippingDate").value(String.valueOf("2023-11-23T03:37:30.111+00:00")))
                .andExpect(jsonPath("$[0].delivered").value(true))

                .andExpect(jsonPath("$[1].orderId").value(14))
                .andExpect(jsonPath("$[1].productQuantity").value(1))
                .andExpect(jsonPath("$[1].productPrice").value(79.99))
                .andExpect(jsonPath("$[1].subTotal").value(79.99))
                .andExpect(jsonPath("$[1].purchaseDate").value(String.valueOf("2023-11-23T03:37:30.111+00:00")))
                .andExpect(jsonPath("$[1].shippingDate").value(String.valueOf("2023-11-23T03:37:30.111+00:00")))
                .andExpect(jsonPath("$[1].delivered").value(true));

        // Verify that orderService.getAllOrders was called
        verify(orderService).getAllOrders();
    }
    
    @Test
    public void testGetByOrderId() throws Exception {
        // Mock data for testing
        OrderResource order = new OrderResource();
        order.setOrderId(1L);
        order.setProductQuantity(2);
        order.setProductPrice(1000.99);
        order.setSubTotal(2001.98);
        order.setPurchaseDate(Date.from(Instant.parse("2023-11-23T03:37:30.111Z")));
        order.setShippingDate(Date.from(Instant.parse("2023-11-23T03:37:30.111Z")));
        order.setDelivered(true);

        // Mock the orderService.getByOrderId method
        when(orderService.getByOrderId(anyLong())).thenReturn(order);

        // Perform the GET request
        mockMvc.perform(get("/getByOrderId/{orderId}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.orderId").value(1L))
                .andExpect(jsonPath("$.productQuantity").value(2))
                .andExpect(jsonPath("$.productPrice").value(1000.99))
                .andExpect(jsonPath("$.subTotal").value(2001.98))
                .andExpect(jsonPath("$.purchaseDate").value(String.valueOf("2023-11-23T03:37:30.111+00:00")))
                .andExpect(jsonPath("$.shippingDate").value(String.valueOf("2023-11-23T03:37:30.111+00:00")))
                .andExpect(jsonPath("$.delivered").value(true));

        // Verify that orderService.getByOrderId was called
        verify(orderService).getByOrderId(1L);
    }

}
