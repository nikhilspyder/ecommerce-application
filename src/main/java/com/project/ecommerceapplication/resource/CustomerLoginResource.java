package com.project.ecommerceapplication.resource;

import java.io.Serializable;

public class CustomerLoginResource implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String customerEmail;
	
	private String password;

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "CustomerLoginResource [customerEmail=" + customerEmail + ", password=" + password + "]";
	}

}
