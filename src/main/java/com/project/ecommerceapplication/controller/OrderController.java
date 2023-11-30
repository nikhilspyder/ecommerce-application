package com.project.ecommerceapplication.controller;

import java.util.List;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.ecommerceapplication.resource.OrderPlaceResource;
import com.project.ecommerceapplication.resource.OrderResource;
import com.project.ecommerceapplication.service.OrderService;

@RestController
@RequestMapping("/")
public class OrderController {
	
    private static final Logger LOGGER = LogManager.getLogger(OrderController.class);
    Counter placeOrderCounter;
    Counter getOrdersCustomer;
    public OrderController(MeterRegistry registry) {
        placeOrderCounter = Counter.builder("place_order_counter")
                .description("Number of orders places")
                .register(registry);
        getOrdersCustomer = Counter.builder("get_all_orders_counter")
                .description("Number of getAllOrders requests")
                .register(registry);
    }
    @Autowired
    private OrderService orderService;

    @PostMapping("/placeOrder")
    public ResponseEntity<?> placeOrder(@RequestBody List<OrderPlaceResource> orderPlaceResourceList) {
    	
    	LOGGER.info("Inside OrderController - placeOrder");
    	placeOrderCounter.increment();
    	try {
	    	List<OrderResource> order = orderService.placeOrder(orderPlaceResourceList);
	        return new ResponseEntity<>(order, HttpStatus.CREATED);
    	}catch(Exception e) {
    		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    	}
    }
    
    @GetMapping("/getAllOrders")
    public ResponseEntity<List<OrderResource>> getAllOrders(@RequestParam(required = false) String customerId) {
    	
    	LOGGER.info("Inside OrderController - getAllOrders");
    	getOrdersCustomer.increment();
    	try {
    		List<OrderResource> orders = orderService.getAllOrders(customerId);

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
    public ResponseEntity<?> getByOrderId(@PathVariable Long orderId) {
    	
    	LOGGER.info("Inside OrderController - getByOrderId");
        try {
            List<OrderResource> orderResponseList = orderService.getByOrderId(orderId);

            if (!CollectionUtils.isEmpty(orderResponseList)) {
                return new ResponseEntity<>(orderResponseList, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}