package com.project.ecommerceapplication.service;

import java.util.List;

import com.project.ecommerceapplication.resource.OrderPlaceResource;
import com.project.ecommerceapplication.resource.OrderResource;

public interface OrderService {
	
	public List<OrderResource> placeOrder(List<OrderPlaceResource> orderResourceList);

}
