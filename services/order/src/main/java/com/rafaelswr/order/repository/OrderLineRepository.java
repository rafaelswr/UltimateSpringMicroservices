package com.rafaelswr.order.repository;

import com.rafaelswr.order.orderLine.OrderLine;
import com.rafaelswr.order.orderLine.OrderLineResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderLineRepository extends JpaRepository<OrderLine, Long> {


    List<OrderLine> findAllByOrderId(Long orderId);
}
