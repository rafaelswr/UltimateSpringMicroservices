package com.rafaelswr.order.service;

import com.rafaelswr.order.customer.CustomerClient;
import com.rafaelswr.order.exception.BusinessException.BusinessException;
import com.rafaelswr.order.order.OrderMapper;
import com.rafaelswr.order.order.OrderRequest;
import com.rafaelswr.order.orderLine.OrderLineRequest;
import com.rafaelswr.order.product.ProductClient;
import com.rafaelswr.order.product.PurchaseRequest;
import com.rafaelswr.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final CustomerClient customerClient;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final ProductClient productClient;
    private final OrderLineService orderLineService;

    @Autowired
    public OrderService(CustomerClient customer, CustomerClient customerClient, OrderRepository orderRepository, OrderMapper orderMapper, ProductClient productClient, OrderLineService orderLineService) {
        this.customerClient = customerClient;
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.productClient = productClient;
        this.orderLineService = orderLineService;
    }

    public Long createOrder(OrderRequest orderRequest) {
        //todo check the customer -customer MS - openFeign
        var customer = customerClient.findCustomerById(orderRequest.customerId())
                .orElseThrow(() -> new BusinessException("Cannot create order :: no customer found !"));

        //todo purchase the products -Product MS- RestTemplate
        productClient.purchaseProducts(orderRequest.products());

        //todo persist order
        var order = orderRepository.save(orderMapper.toOrder(orderRequest));

        //todo persist order lines
        for (PurchaseRequest purchaseRequest : orderRequest.products()){
            orderLineService.saveOrderLine( new OrderLineRequest(
                    null,
                    order.getId(),
                    purchaseRequest.product_id(),
                    purchaseRequest.quantity())
            );
        }
        //todo start payment process
        //todo send the order confirmation -- notification ms (kafka)
        return null;
    }

}
