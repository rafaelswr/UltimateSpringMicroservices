package com.rafaelswr.order.repository;

import com.rafaelswr.order.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {


}
