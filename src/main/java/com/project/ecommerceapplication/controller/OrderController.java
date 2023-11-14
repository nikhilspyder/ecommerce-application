package com.project.ecommerceapplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    private OrderService orderService;

    @PostMapping("/placeOrder")
    public ResponseEntity<?> placeOrder(@RequestBody List<OrderPlaceResource> orderPlaceResourceList) {
    	List<OrderResource> order = orderService.placeOrder(orderPlaceResourceList);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }
}