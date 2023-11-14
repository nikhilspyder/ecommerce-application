package com.project.ecommerceapplication.service.impl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.project.ecommerceapplication.entity.OrderEntity;
import com.project.ecommerceapplication.mapper.OrderMapper;
import com.project.ecommerceapplication.resource.CustomerLoginResource;
import com.project.ecommerceapplication.resource.CustomerRegisterResource;
import com.project.ecommerceapplication.resource.OrderPlaceResource;
import com.project.ecommerceapplication.resource.OrderResource;
import com.project.ecommerceapplication.resource.ProductResource;
import com.project.ecommerceapplication.respository.OrderRepository;
import com.project.ecommerceapplication.service.CustomerService;
import com.project.ecommerceapplication.service.OrderService;
import com.project.ecommerceapplication.service.ProductService;

@Service
public class OrderServiceImpl implements OrderService {
	
	private static final Logger LOGGER = LogManager.getLogger(OrderServiceImpl.class);

	private final OrderMapper orderMapper = Mappers.getMapper(OrderMapper.class);
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private OrderRepository orderRepository;

	@Override
	public List<OrderResource> placeOrder(List<OrderPlaceResource> orderPlaceResourceList) {
		
		List<OrderResource> responseOrderResourceList = new ArrayList<>();
		
		if(!CollectionUtils.isEmpty(orderPlaceResourceList)) {
			
			orderPlaceResourceList.stream().forEach(orderPlaceResource ->{
				
				LOGGER.info("Placing Order for Customer - " + orderPlaceResource.getCustomerId());
				
				long productId = orderPlaceResource.getProductId();
				
				int productQuantity = orderPlaceResource.getProductQuantity();
				
				OrderResource orderResource = new OrderResource();
				
				ProductResource productResource  = productService.getProductByProductId(productId);
				orderResource.setProductId(productResource);
				
				CustomerRegisterResource customerResource = customerService.getCustomer(orderPlaceResource.getCustomerId());
				
				LOGGER.info(" ** Customer Response - " + customerResource);
				
				orderResource.setCustomerId(customerResource);
				
				double productPrice = productResource.getPrice();
				orderResource.setProductPrice(productResource.getPrice());
				
				orderResource.setProductQuantity(productQuantity);
				
				orderResource.setSubTotal(productPrice * productQuantity);
				orderResource.setDelivered(true);
				
				LocalDateTime localDateTime = LocalDateTime.now();
				Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
				orderResource.setPurchaseDate(date);
				
				orderResource.setShippingDate(date);
				
				LOGGER.info("Constructed Order Object - " + orderResource);
				
				OrderEntity orderEntity = orderMapper.mapResourceToEntity(orderResource);
				
				try {
					OrderEntity res = orderRepository.save(orderEntity);	
					
					responseOrderResourceList.add(orderMapper.mapEntityToResource(res));
					
				}catch(Exception e) {
					LOGGER.error("Error while placing order : " + e.getMessage(), e);
		            throw e;
				}
				
			});
			
		}
		
		return responseOrderResourceList;
	}

}
