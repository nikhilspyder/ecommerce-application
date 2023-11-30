package com.project.ecommerceapplication.controller;

import javax.validation.Valid;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.ecommerceapplication.resource.CustomerLoginResource;
import com.project.ecommerceapplication.resource.CustomerRegisterResource;
import com.project.ecommerceapplication.service.CustomerService;

@RestController
@RequestMapping("/")
public class CustomerController {

    Counter loginCounter;
    Counter registerCustomer;
    Counter getCustomer;
    Counter updateCustomer;

    public CustomerController(MeterRegistry registry) {
        loginCounter = Counter.builder("login_counter")
                .description("Number of logins")
                .register(registry);
        registerCustomer = Counter.builder("register_counter")
                .description("Number of new users registered")
                .register(registry);
        getCustomer = Counter.builder("get_customer_counter")
                .description("Number of get customer requests")
                .register(registry);
        updateCustomer = Counter.builder("update_customer_counter")
                .description("Number of update customer requests")
                .register(registry);
    }

    private static final Logger LOGGER = LogManager.getLogger(CustomerController.class);

    @Autowired
    private CustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity<CustomerRegisterResource> registerCustomer(@Valid @RequestBody CustomerRegisterResource customerResource) {

        LOGGER.info("Inside CustomerController - registerCustomer");
        registerCustomer.increment();
        try{
        	CustomerRegisterResource response = customerService.register(customerResource);
            LOGGER.info("Executed CustomerController - registerCustomer");
            LOGGER.info("response - " + response);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }catch(Exception e){
            LOGGER.error("Caught Exception - "+ e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> loginCustomer(@Valid @RequestBody CustomerLoginResource customerResource) {

        LOGGER.info("Inside CustomerController - loginCustomer");
        loginCounter.increment();
        try{
        	CustomerRegisterResource response = customerService.login(customerResource);
            LOGGER.info("Executed CustomerController - loginCustomer");
            LOGGER.info("response - " + response);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }catch(Exception e){
            LOGGER.error("Caught Exception - "+ e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
    
    
    @GetMapping("/getCustomer")
    public ResponseEntity<?> getCustomer(@RequestParam Long customerId) {

        LOGGER.info("Inside CustomerController - getCustomer");
        getCustomer.increment();
        try{
        	CustomerRegisterResource response = customerService.getCustomer(customerId);
            LOGGER.info("Executed CustomerController - getCustomer");
            LOGGER.info("response - " + response);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }catch(Exception e){
            LOGGER.error("Caught Exception - "+ e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
    
    @PutMapping("/updateCustomer")
    public ResponseEntity<CustomerRegisterResource> updateCustomer(@Valid @RequestBody CustomerRegisterResource customerResource) {

        LOGGER.info("Inside CustomerController - updateCustomer");
        updateCustomer.increment();
        try{
        	CustomerRegisterResource response = customerService.updateCustomer(customerResource);
            LOGGER.info("Executed CustomerController - updateCustomer");
            LOGGER.info("response - " + response);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }catch(Exception e){
            LOGGER.error("Caught Exception - "+ e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
