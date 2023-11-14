package com.project.ecommerceapplication.mapper;

import org.mapstruct.Mapper;

import com.project.ecommerceapplication.entity.CustomerRegisterEntity;
import com.project.ecommerceapplication.entity.OrderEntity;
import com.project.ecommerceapplication.entity.ProductEntity;
import com.project.ecommerceapplication.resource.CustomerRegisterResource;
import com.project.ecommerceapplication.resource.OrderResource;
import com.project.ecommerceapplication.resource.ProductResource;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderEntity mapResourceToEntity(OrderResource orderResource);

    OrderResource mapEntityToResource(OrderEntity orderEntity);

    CustomerRegisterEntity mapCustomerResourceToEntity(CustomerRegisterResource customerResource);

    CustomerRegisterResource mapCustomerEntityToResource(CustomerRegisterEntity customerEntity);

    ProductEntity mapProductResourceToEntity(ProductResource productResource);

    ProductResource mapProductEntityToResource(ProductEntity productEntity);
}
