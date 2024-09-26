package com.rafaelswr.order.orderLine;

import com.rafaelswr.order.service.OrderLineService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("/order-line")
@RequiredArgsConstructor
public class OrderLineController {

    private final OrderLineService orderLineService;

    //find all orderlines for specific order
    @GetMapping("/order/{order-id}")
    public ResponseEntity<List<OrderLineResponse>> findAllOrderLinesByOrder(@PathVariable("order-id") Long orderId){
        return ResponseEntity.ok(orderLineService.findAllOrderLinesByOrderId(orderId));
    }



}
