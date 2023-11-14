package com.project.ecommerceapplication.respository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.ecommerceapplication.entity.CustomerRegisterEntity;

@Repository
public interface CustomerRepository extends CrudRepository<CustomerRegisterEntity, Long> {

    @Query(value = "SELECT * FROM customer WHERE customerEmail = :customerEmail", nativeQuery = true)
    Optional<CustomerRegisterEntity> findByCustomerEmail(@Param("customerEmail") String customerEmail);

}
