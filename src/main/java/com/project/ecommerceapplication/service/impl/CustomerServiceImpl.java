package com.project.ecommerceapplication.service.impl;

import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.project.ecommerceapplication.entity.CustomerRegisterEntity;
import com.project.ecommerceapplication.exception.CustomerNotRegisteredException;
import com.project.ecommerceapplication.mapper.CustomerMapper;
import com.project.ecommerceapplication.resource.CustomerLoginResource;
import com.project.ecommerceapplication.resource.CustomerRegisterResource;
import com.project.ecommerceapplication.respository.CustomerRepository;
import com.project.ecommerceapplication.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

    private static final Logger LOGGER = LogManager.getLogger(CustomerServiceImpl.class);

    private final CustomerMapper customerMapper = Mappers.getMapper(CustomerMapper.class);
    
    @Autowired
    private CustomerRepository customerRepository;

    @Value("${ecommerceThreads:5}")
    private int threads;

    private ExecutorService executorService = Executors.newFixedThreadPool(5); // Adjust thread pool size as needed

    private Lock customerLock = new ReentrantLock();

    @SuppressWarnings("unused")
	@Transactional
    public CustomerRegisterResource register(CustomerRegisterResource customerRegisterResource) {
        LOGGER.info("Inside CustomerServiceImpl - register");

        AtomicReference<CustomerRegisterResource> response =  new AtomicReference<>();

        try {

            LOGGER.info("Customer Email - " + customerRegisterResource.getCustomerEmail());

            CustomerRegisterEntity customerRegisterEntity = customerMapper.mapResourceToEntity(customerRegisterResource);

            customerRepository.save(customerRegisterEntity); // Save the trainEntity

            LOGGER.info("customerRegisterEntity "+customerRegisterEntity);

            CustomerRegisterResource customerResponse =  customerMapper.mapEntityToResource(customerRegisterEntity);

            LOGGER.info("customerResponse - " + customerResponse);

            response.set(customerResponse);

            LOGGER.info("response ---- " + response.get());

            LOGGER.info("Customer Registered successfully.");
        } catch (Exception e) {
            LOGGER.error("Error while regsitering user: " + e.getMessage(), e);
            throw e;
        }

        if(response != null)
            return response.get();
        else
            return null;
    }

	@Override
	public CustomerRegisterResource login(CustomerLoginResource customerLoginResource) {
		 LOGGER.info("Inside CustomerServiceImpl - register");
		
		 try {
			 LOGGER.info("Customer Email - " + customerLoginResource.getCustomerEmail());
			 
			 Optional<CustomerRegisterEntity> customerEntity = customerRepository.findByCustomerEmail(customerLoginResource.getCustomerEmail());
			
			 if(!customerEntity.isPresent()) {
				 
				 LOGGER.error("Customer with email " + customerLoginResource.getCustomerEmail() + " is not registered");
	            throw new CustomerNotRegisteredException("Customer with email " + customerLoginResource.getCustomerEmail() + " is not registered");
			 }
			 
			 CustomerRegisterResource customerResponse =  customerMapper.mapEntityToResource(customerEntity.get());
			 
			 LOGGER.info("customerResponse - " + customerResponse);
			 
			 return customerResponse;
			 
		 }catch(Exception e) {
			 LOGGER.error("Error while login: " + e.getMessage(), e);
	         throw e;
		 }
		
	}
	
	public CustomerRegisterResource updateCustomer(CustomerRegisterResource customerResource) {
		
		 LOGGER.info("Inside CustomerServiceImpl - updateCustomer");
		 
		 try {

	            LOGGER.info("Customer Email - " + customerResource.getCustomerEmail());
	            
	            Optional<CustomerRegisterEntity> entity = customerRepository.findByCustomerEmail(customerResource.getCustomerEmail());
	            
	            if(!entity.isPresent()) {
	            	LOGGER.error("Customer is not registered with email - " + customerResource.getCustomerEmail());
	            	throw new CustomerNotRegisteredException("Customer with email " + customerResource.getCustomerEmail() + " is not registered");
	            }
	            
	            long id = entity.get().getId();
	            
	            customerResource.setId(id);

	            CustomerRegisterEntity customerRegisterEntity = customerMapper.mapResourceToEntity(customerResource);

	            customerRepository.save(customerRegisterEntity); // Save the trainEntity

	            LOGGER.info("customerRegisterEntity " + customerRegisterEntity);

	            CustomerRegisterResource customerResponse =  customerMapper.mapEntityToResource(customerRegisterEntity);

	            LOGGER.info("customerResponse - " + customerResponse);

	            LOGGER.info("Customer Updated successfully.");
			 
		 }catch(Exception e) {
			 LOGGER.error("Error while login: " + e.getMessage(), e);
	         throw e;
		 }
		
		return customerResource;
		
	}
}
