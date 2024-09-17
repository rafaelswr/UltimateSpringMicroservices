package com.rafaelswr.order.repository;

import com.rafaelswr.order.orderLine.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderLineRepository extends JpaRepository<OrderLine, Long> {


}
