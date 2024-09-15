package com.rafaelswr.product.repository;

import com.rafaelswr.product.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByIdInOrderById(List<Long> productsByIds);
}
