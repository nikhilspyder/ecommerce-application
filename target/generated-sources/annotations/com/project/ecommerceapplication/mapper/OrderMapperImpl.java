package com.project.ecommerceapplication.mapper;

import com.project.ecommerceapplication.entity.CustomerRegisterEntity;
import com.project.ecommerceapplication.entity.OrderEntity;
import com.project.ecommerceapplication.entity.ProductEntity;
import com.project.ecommerceapplication.resource.CustomerRegisterResource;
import com.project.ecommerceapplication.resource.OrderResource;
import com.project.ecommerceapplication.resource.ProductResource;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-11-14T12:35:25-0600",
    comments = "version: 1.4.2.Final, compiler: Eclipse JDT (IDE) 3.35.0.v20230814-2020, environment: Java 17.0.8.1 (Eclipse Adoptium)"
)
@Component
public class OrderMapperImpl implements OrderMapper {

    @Override
    public OrderEntity mapResourceToEntity(OrderResource orderResource) {
        if ( orderResource == null ) {
            return null;
        }

        OrderEntity orderEntity = new OrderEntity();

        orderEntity.setCustomerId( mapCustomerResourceToEntity( orderResource.getCustomerId() ) );
        orderEntity.setDelivered( orderResource.isDelivered() );
        orderEntity.setOrderId( orderResource.getOrderId() );
        orderEntity.setProductId( mapProductResourceToEntity( orderResource.getProductId() ) );
        orderEntity.setProductPrice( orderResource.getProductPrice() );
        orderEntity.setProductQuantity( orderResource.getProductQuantity() );
        orderEntity.setPurchaseDate( orderResource.getPurchaseDate() );
        orderEntity.setShippingDate( orderResource.getShippingDate() );
        orderEntity.setSubTotal( orderResource.getSubTotal() );

        return orderEntity;
    }

    @Override
    public OrderResource mapEntityToResource(OrderEntity orderEntity) {
        if ( orderEntity == null ) {
            return null;
        }

        OrderResource orderResource = new OrderResource();

        orderResource.setCustomerId( mapCustomerEntityToResource( orderEntity.getCustomerId() ) );
        orderResource.setDelivered( orderEntity.isDelivered() );
        orderResource.setOrderId( orderEntity.getOrderId() );
        orderResource.setProductId( mapProductEntityToResource( orderEntity.getProductId() ) );
        orderResource.setProductPrice( orderEntity.getProductPrice() );
        orderResource.setProductQuantity( orderEntity.getProductQuantity() );
        orderResource.setPurchaseDate( orderEntity.getPurchaseDate() );
        orderResource.setShippingDate( orderEntity.getShippingDate() );
        orderResource.setSubTotal( orderEntity.getSubTotal() );

        return orderResource;
    }

    @Override
    public CustomerRegisterEntity mapCustomerResourceToEntity(CustomerRegisterResource customerResource) {
        if ( customerResource == null ) {
            return null;
        }

        CustomerRegisterEntity customerRegisterEntity = new CustomerRegisterEntity();

        customerRegisterEntity.setAddress( customerResource.getAddress() );
        customerRegisterEntity.setCity( customerResource.getCity() );
        customerRegisterEntity.setCustomerEmail( customerResource.getCustomerEmail() );
        customerRegisterEntity.setCustomerName( customerResource.getCustomerName() );
        customerRegisterEntity.setFirstName( customerResource.getFirstName() );
        customerRegisterEntity.setId( customerResource.getId() );
        customerRegisterEntity.setLastName( customerResource.getLastName() );
        customerRegisterEntity.setPassword( customerResource.getPassword() );
        customerRegisterEntity.setPhone( customerResource.getPhone() );
        customerRegisterEntity.setPostcode( customerResource.getPostcode() );

        return customerRegisterEntity;
    }

    @Override
    public CustomerRegisterResource mapCustomerEntityToResource(CustomerRegisterEntity customerEntity) {
        if ( customerEntity == null ) {
            return null;
        }

        CustomerRegisterResource customerRegisterResource = new CustomerRegisterResource();

        customerRegisterResource.setAddress( customerEntity.getAddress() );
        customerRegisterResource.setCity( customerEntity.getCity() );
        customerRegisterResource.setCustomerEmail( customerEntity.getCustomerEmail() );
        customerRegisterResource.setCustomerName( customerEntity.getCustomerName() );
        customerRegisterResource.setFirstName( customerEntity.getFirstName() );
        customerRegisterResource.setId( customerEntity.getId() );
        customerRegisterResource.setLastName( customerEntity.getLastName() );
        customerRegisterResource.setPassword( customerEntity.getPassword() );
        customerRegisterResource.setPhone( customerEntity.getPhone() );
        customerRegisterResource.setPostcode( customerEntity.getPostcode() );

        return customerRegisterResource;
    }

    @Override
    public ProductEntity mapProductResourceToEntity(ProductResource productResource) {
        if ( productResource == null ) {
            return null;
        }

        ProductEntity productEntity = new ProductEntity();

        productEntity.setCategory( productResource.getCategory() );
        productEntity.setDescription( productResource.getDescription() );
        productEntity.setId( productResource.getId() );
        productEntity.setName( productResource.getName() );
        productEntity.setPrice( productResource.getPrice() );
        productEntity.setStock( productResource.getStock() );

        return productEntity;
    }

    @Override
    public ProductResource mapProductEntityToResource(ProductEntity productEntity) {
        if ( productEntity == null ) {
            return null;
        }

        ProductResource productResource = new ProductResource();

        productResource.setCategory( productEntity.getCategory() );
        productResource.setDescription( productEntity.getDescription() );
        productResource.setId( productEntity.getId() );
        productResource.setName( productEntity.getName() );
        productResource.setPrice( productEntity.getPrice() );
        productResource.setStock( productEntity.getStock() );

        return productResource;
    }
}
