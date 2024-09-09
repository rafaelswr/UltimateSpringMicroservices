package com.rafaelswr.customer.repository;


import com.rafaelswr.customer.customer.Customer;
import org.springframework.data.mongodb.core.MongoAdminOperations;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends MongoRepository<Customer,String> {
    @Query("{ 'id' : ?0 }")
    Optional<Customer> findByDocId(String id);
}
