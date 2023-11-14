package com.project.ecommerceapplication.service;

import javax.validation.Valid;

import com.project.ecommerceapplication.resource.CustomerLoginResource;
import com.project.ecommerceapplication.resource.CustomerRegisterResource;

public interface CustomerService {

	public CustomerRegisterResource register(CustomerRegisterResource customerRegisterResource);
	
	public CustomerRegisterResource login(CustomerLoginResource customerLoginResource);

	public CustomerRegisterResource updateCustomer(CustomerRegisterResource customerResource);
	
	public CustomerRegisterResource getCustomer(Long customerId);

}
