package com.project.ecommerceapplication;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.ecommerceapplication.resource.CustomerLoginResource;
import com.project.ecommerceapplication.resource.CustomerRegisterResource;
import com.project.ecommerceapplication.service.CustomerService;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CustomerService customerService;

    @Test
    public void testRegisterCustomer() throws Exception {
        CustomerRegisterResource customerResource = new CustomerRegisterResource();
        customerResource.setId(1L);
        customerResource.setCustomerName("Mathhew");
        customerResource.setCustomerEmail("Matthew@example.com");
        customerResource.setFirstName("Mathhew");
        customerResource.setLastName("Perry");
        customerResource.setPassword("securepassword");
        customerResource.setAddress("456 Main St");
        customerResource.setPostcode(60616);
        customerResource.setCity("New York");
        customerResource.setPhone(1234567890);

        String customerJson = objectMapper.writeValueAsString(customerResource);

        // Mock the customerService.register method
        when(customerService.register(any(CustomerRegisterResource.class))).thenReturn(customerResource);

        // Perform the POST request
        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(customerJson))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.customerEmail").value("Matthew@example.com"))
                .andExpect(jsonPath("$.password").value("securepassword"))
                .andExpect(jsonPath("$.firstName").value("Mathhew"))
                .andExpect(jsonPath("$.lastName").value("Perry"))
                .andExpect(jsonPath("$.address").value("456 Main St"))
                .andExpect(jsonPath("$.postcode").value(60616))
                .andExpect(jsonPath("$.city").value("New York"))
                .andExpect(jsonPath("$.phone").value(1234567890));
                

        // Verify that customerService.register was called with the expected argument
        verify(customerService).register(any(CustomerRegisterResource.class));
    }
    
    @Test
    public void testLoginCustomer() throws Exception {
        // Create a CustomerLoginResource for testing
        CustomerLoginResource customerLoginResource = new CustomerLoginResource();
        customerLoginResource.setCustomerEmail("Matthew@example.com");
        customerLoginResource.setPassword("securepassword");
        
        CustomerRegisterResource customerResource = new CustomerRegisterResource();
        customerResource.setCustomerEmail("Matthew@example.com");
        customerResource.setPassword("securepassword");

        // Convert the CustomerLoginResource to JSON
        String customerJson = objectMapper.writeValueAsString(customerLoginResource);

        // Mock the customerService.login method
        when(customerService.login(any(CustomerLoginResource.class))).thenReturn(customerResource);

        // Perform the POST request
        mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(customerJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.customerEmail").value("Matthew@example.com"))
                .andExpect(jsonPath("$.password").value("securepassword"));

        // Verify that customerService.login was called with the expected argument
        verify(customerService).login(any(CustomerLoginResource.class));
    }
    
    @Test
    public void testGetCustomer() throws Exception {
    	
    	CustomerRegisterResource customerResource = new CustomerRegisterResource();
        customerResource.setCustomerEmail("Matthew@example.com");
        customerResource.setFirstName("Mathhew");
        customerResource.setPassword("securepassword");
    	
        // Mock the customerService.getCustomer method
        when(customerService.getCustomer(anyLong())).thenReturn(customerResource);

        // Perform the GET request
        mockMvc.perform(get("/getCustomer")
                        .param("customerId", "123"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.firstName").value("Mathhew"))
                .andExpect(jsonPath("$.customerEmail").value("Matthew@example.com"))
                .andExpect(jsonPath("$.password").value("securepassword"));

        // Verify that customerService.getCustomer was called with the expected argument
        verify(customerService).getCustomer(123L);
    }
    
    @Test
    public void testUpdateCustomer() throws Exception {
        // Create a CustomerRegisterResource for testing
    	CustomerRegisterResource customerResource = new CustomerRegisterResource();
        customerResource.setId(1L);
        customerResource.setCustomerName("Mathhew");
        customerResource.setCustomerEmail("Matthew@example.com");
        customerResource.setFirstName("Mathhew");
        customerResource.setLastName("Perry");
        customerResource.setPassword("securepassword");
        customerResource.setAddress("456 Main St");
        customerResource.setPostcode(60616);
        customerResource.setCity("New York");
        customerResource.setPhone(1234567890);

        // Convert the CustomerRegisterResource to JSON
        String customerJson = objectMapper.writeValueAsString(customerResource);

        // Mock the customerService.updateCustomer method
        when(customerService.updateCustomer(any(CustomerRegisterResource.class))).thenReturn(customerResource);

        // Perform the PUT request
        mockMvc.perform(put("/updateCustomer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(customerJson))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.customerEmail").value("Matthew@example.com"))
                .andExpect(jsonPath("$.password").value("securepassword"))
                .andExpect(jsonPath("$.firstName").value("Mathhew"))
                .andExpect(jsonPath("$.lastName").value("Perry"))
                .andExpect(jsonPath("$.address").value("456 Main St"))
                .andExpect(jsonPath("$.postcode").value(60616))
                .andExpect(jsonPath("$.city").value("New York"))
                .andExpect(jsonPath("$.phone").value(1234567890));

        // Verify that customerService.updateCustomer was called with the expected argument
        verify(customerService).updateCustomer(any(CustomerRegisterResource.class));
    }
    
}

