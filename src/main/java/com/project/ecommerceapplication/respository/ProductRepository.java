package com.project.ecommerceapplication.respository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.ecommerceapplication.entity.ProductEntity;

@Repository
public interface ProductRepository extends CrudRepository<ProductEntity, Long> {

	@Query(value = "SELECT * FROM product WHERE category = :category", nativeQuery = true)
    Optional<List<ProductEntity>> findProductsByCategory(@Param("category") String category);
	
	@Query(value = "SELECT * FROM product WHERE id = :id", nativeQuery = true)
    Optional<ProductEntity> findByProductId(@Param("id") Long id);
}
