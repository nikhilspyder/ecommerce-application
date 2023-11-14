package com.project.ecommerceapplication.mapper;

import com.project.ecommerceapplication.entity.CustomerRegisterEntity;
import com.project.ecommerceapplication.resource.CustomerRegisterResource;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-11-14T11:36:09-0600",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.9 (Oracle Corporation)"
)
public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public CustomerRegisterEntity mapResourceToEntity(CustomerRegisterResource customerRegisterResource) {
        if ( customerRegisterResource == null ) {
            return null;
        }

        CustomerRegisterEntity customerRegisterEntity = new CustomerRegisterEntity();

        customerRegisterEntity.setId( customerRegisterResource.getId() );
        customerRegisterEntity.setCustomerName( customerRegisterResource.getCustomerName() );
        customerRegisterEntity.setCustomerEmail( customerRegisterResource.getCustomerEmail() );
        customerRegisterEntity.setFirstName( customerRegisterResource.getFirstName() );
        customerRegisterEntity.setLastName( customerRegisterResource.getLastName() );
        customerRegisterEntity.setPassword( customerRegisterResource.getPassword() );
        customerRegisterEntity.setAddress( customerRegisterResource.getAddress() );
        customerRegisterEntity.setPostcode( customerRegisterResource.getPostcode() );
        customerRegisterEntity.setCity( customerRegisterResource.getCity() );
        customerRegisterEntity.setPhone( customerRegisterResource.getPhone() );

        return customerRegisterEntity;
    }

    @Override
    public CustomerRegisterResource mapEntityToResource(CustomerRegisterEntity customerEntity) {
        if ( customerEntity == null ) {
            return null;
        }

        CustomerRegisterResource customerRegisterResource = new CustomerRegisterResource();

        customerRegisterResource.setId( customerEntity.getId() );
        customerRegisterResource.setCustomerName( customerEntity.getCustomerName() );
        customerRegisterResource.setCustomerEmail( customerEntity.getCustomerEmail() );
        customerRegisterResource.setFirstName( customerEntity.getFirstName() );
        customerRegisterResource.setLastName( customerEntity.getLastName() );
        customerRegisterResource.setPassword( customerEntity.getPassword() );
        customerRegisterResource.setAddress( customerEntity.getAddress() );
        customerRegisterResource.setPostcode( customerEntity.getPostcode() );
        customerRegisterResource.setCity( customerEntity.getCity() );
        customerRegisterResource.setPhone( customerEntity.getPhone() );

        return customerRegisterResource;
    }
}
