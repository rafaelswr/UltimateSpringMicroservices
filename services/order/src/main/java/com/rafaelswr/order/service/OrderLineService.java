package com.rafaelswr.order.service;

import com.rafaelswr.order.order.OrderMapper;
import com.rafaelswr.order.orderLine.OrderLine;
import com.rafaelswr.order.orderLine.OrderLineMapper;
import com.rafaelswr.order.orderLine.OrderLineRequest;
import com.rafaelswr.order.orderLine.OrderLineResponse;
import com.rafaelswr.order.repository.OrderLineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderLineService {

        private final OrderLineRepository orderLineRepository;

        private final OrderLineMapper orderLineMapper;

        public void saveOrderLine(OrderLineRequest orderLineRequest){
            OrderLine order = orderLineMapper.toOrderLine(orderLineRequest);
            orderLineRepository.save(order);

        }
        public List<OrderLineResponse> findAllOrderLinesByOrderId(Long orderId) {
            return orderLineRepository.findAllByOrderId(orderId).stream()
                    .map(orderLineMapper::toOrderlineResponse).toList();
        }
}
