package com.project.ecommerceapplication.mapper;

import com.project.ecommerceapplication.entity.ProductEntity;
import com.project.ecommerceapplication.resource.ProductResource;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-11-14T11:36:09-0600",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.9 (Oracle Corporation)"
)
public class ProductMapperImpl implements ProductMapper {

    @Override
    public ProductEntity mapResourceToEntity(ProductResource productResource) {
        if ( productResource == null ) {
            return null;
        }

        ProductEntity productEntity = new ProductEntity();

        productEntity.setId( productResource.getId() );
        productEntity.setName( productResource.getName() );
        productEntity.setDescription( productResource.getDescription() );
        productEntity.setPrice( productResource.getPrice() );
        productEntity.setStock( productResource.getStock() );
        productEntity.setCategory( productResource.getCategory() );

        return productEntity;
    }

    @Override
    public ProductResource mapEntityToResource(ProductEntity productEntity) {
        if ( productEntity == null ) {
            return null;
        }

        ProductResource productResource = new ProductResource();

        productResource.setId( productEntity.getId() );
        productResource.setName( productEntity.getName() );
        productResource.setDescription( productEntity.getDescription() );
        productResource.setPrice( productEntity.getPrice() );
        productResource.setStock( productEntity.getStock() );
        productResource.setCategory( productEntity.getCategory() );

        return productResource;
    }
}
