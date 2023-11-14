package com.project.ecommerceapplication.respository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.ecommerceapplication.entity.CustomerRegisterEntity;
import com.project.ecommerceapplication.entity.OrderEntity;

@Repository
public interface OrderRepository extends CrudRepository<OrderEntity, Long>{

	@Query(value = "SELECT * FROM customer WHERE customer_email = :customerEmail", nativeQuery = true)
    Optional<CustomerRegisterEntity> findByCustomerEmail(@Param("customerEmail") String customerEmail);
}
