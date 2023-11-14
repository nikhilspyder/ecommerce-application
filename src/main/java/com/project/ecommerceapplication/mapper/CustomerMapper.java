package com.project.ecommerceapplication.mapper;

import org.mapstruct.Mapper;

import com.project.ecommerceapplication.entity.CustomerRegisterEntity;
import com.project.ecommerceapplication.resource.CustomerRegisterResource;

@Mapper
public interface CustomerMapper {

	CustomerRegisterEntity mapResourceToEntity(CustomerRegisterResource customerRegisterResource);

	CustomerRegisterResource mapEntityToResource(CustomerRegisterEntity customerEntity);
}
