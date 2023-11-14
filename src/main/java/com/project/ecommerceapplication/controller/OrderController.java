package com.project.ecommerceapplication.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.ecommerceapplication.resource.OrderPlaceResource;
import com.project.ecommerceapplication.resource.OrderResource;
import com.project.ecommerceapplication.service.OrderService;

@RestController
@RequestMapping("/")
public class OrderController {
	
	 private static final Logger LOGGER = LogManager.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

    @PostMapping("/placeOrder")
    public ResponseEntity<?> placeOrder(@RequestBody List<OrderPlaceResource> orderPlaceResourceList) {
    	
    	LOGGER.info("Inside OrderController - placeOrder");
    	
    	try {
	    	List<OrderResource> order = orderService.placeOrder(orderPlaceResourceList);
	        return new ResponseEntity<>(order, HttpStatus.CREATED);
    	}catch(Exception e) {
    		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    	}
    }
    
    @GetMapping("/getAllOrders")
    public ResponseEntity<List<OrderResource>> getAllOrders() {
    	
    	LOGGER.info("Inside OrderController - getAllOrders");
    	
    	try {
    		List<OrderResource> orders = orderService.getAllOrders();

            if (!orders.isEmpty()) {
                return new ResponseEntity<>(orders, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
    	}catch(Exception e) {
    		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    	}
    	
        
    }
    
    @GetMapping("/getByOrderId/{orderId}")
    public ResponseEntity<OrderResource> getByOrderId(@PathVariable Long orderId) {
    	
    	LOGGER.info("Inside OrderController - getByOrderId");
        try {
            OrderResource order = orderService.getByOrderId(orderId);

            if (order != null) {
                return new ResponseEntity<>(order, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}