package com.project.ecommerceapplication.service.impl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

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
		
		LOGGER.info("Inside OrderServiceImpl - placeOrder");
		
		List<OrderResource> responseOrderResourceList = new ArrayList<>();
		
		if(!CollectionUtils.isEmpty(orderPlaceResourceList)) {
			
			Long orderId = generateOrderId();
			
			LOGGER.info(" ** Generated Order id - " + orderId);
			
			orderPlaceResourceList.stream().forEach(orderPlaceResource ->{
				
				LOGGER.info("Placing Order for Customer - " + orderPlaceResource.getCustomerId());
				
				long productId = orderPlaceResource.getProductId();
				
				int productQuantity = orderPlaceResource.getProductQuantity();
				
				OrderResource orderResource = new OrderResource();
				
				ProductResource productResource  = productService.getProductByProductId(productId);
				orderResource.setProductId(productResource);
				
				CustomerRegisterResource customerResource = customerService.getCustomer(orderPlaceResource.getCustomerId());
				
				LOGGER.info("Customer Response - " + customerResource);
				
				orderResource.setCustomerId(customerResource);
				
				double productPrice = productResource.getPrice();
				orderResource.setProductPrice(productResource.getPrice());
				orderResource.setOrderId(orderId);
				
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

	private Long generateOrderId() {
		// Create a Random object
		Random random = new Random();

		// Define the minimum value
		long minValue = 100000L;

		// Generate a random long within the specified range
		long orderId = minValue + (long) (random.nextDouble() * Long.MAX_VALUE);
		return orderId;
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<OrderResource> getAllOrders(String customerId) {
		
		LOGGER.info("Inside OrderServiceImpl - getAllOrders");
		
		try {
			List<OrderResource> orderResources = new ArrayList<>();
			
			Iterable<OrderEntity> orderEntities = null;
			if(StringUtils.isEmpty(customerId)) {
				orderEntities = orderRepository.findAll();
				
				orderEntities.forEach(orderEntity -> {
					OrderResource orderResource = orderMapper.mapEntityToResource(orderEntity);
					orderResources.add(orderResource);
				});
				
			}else {
				Optional<List<OrderEntity>> orderListEntites = orderRepository.findAllOrdersByCustomerId(customerId);
				if(orderListEntites.isPresent()) {
					
					orderListEntites.get().forEach(orderEntity -> {
						OrderResource orderResource = orderMapper.mapEntityToResource(orderEntity);
						orderResources.add(orderResource);
					});
				}
			}

			return orderResources;
	    } catch (Exception e) {
	        LOGGER.error("Error while getAllOrders : " + e.getMessage(), e);
	        throw e;
	    }
		
	}

	@Override
	public List<OrderResource> getByOrderId(Long orderId) {
		
		LOGGER.info("Inside OrderServiceImpl - getByOrderId");

		List<OrderResource> responseList = new ArrayList<>();
		
		try {
	        Optional<List<OrderEntity>> orderEntityList = orderRepository.findByOrderId(orderId);
	        
	        OrderResource orderResource;
			if(orderEntityList.isPresent()) {
	        	
	        	for(OrderEntity orderEntity : orderEntityList.get()) {
	        		orderResource = orderMapper.mapEntityToResource(orderEntity);
	        		responseList.add(orderResource);
	        	}
	        }

	        return responseList;
	    } catch (Exception e) {
	        LOGGER.error("Error while getAllOrders : " + e.getMessage(), e);
	        throw e;
	    }
		
	}

}
