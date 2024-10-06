package com.rafaelswr.order.service;

import com.rafaelswr.order.customer.CustomerClient;
import com.rafaelswr.order.customer.CustomerResponse;
import com.rafaelswr.order.exception.BusinessException.BusinessException;
import com.rafaelswr.order.kafka.OrderConfirmation;
import com.rafaelswr.order.order.OrderMapper;
import com.rafaelswr.order.order.OrderRequest;
import com.rafaelswr.order.order.OrderResponse;
import com.rafaelswr.order.order.PaymentMethod;
import com.rafaelswr.order.orderLine.OrderLineRequest;
import com.rafaelswr.order.payment.PaymentClient;
import com.rafaelswr.order.payment.PaymentRequest;
import com.rafaelswr.order.product.ProductClient;
import com.rafaelswr.order.product.PurchaseRequest;
import com.rafaelswr.order.repository.OrderRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderService {

    private final CustomerClient customerClient;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final ProductClient productClient;
    private final OrderLineService orderLineService;

    private final KafkaOrderProducer kafkaOrderProducer;

    private final PaymentClient paymentClient;

    @Autowired
    public OrderService(CustomerClient customer, CustomerClient customerClient, OrderRepository orderRepository, OrderMapper orderMapper, ProductClient productClient, OrderLineService orderLineService, KafkaOrderProducer kafkaOrderProducer, PaymentClient paymentClient) {
        this.customerClient = customerClient;
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.productClient = productClient;
        this.orderLineService = orderLineService;
        this.kafkaOrderProducer = kafkaOrderProducer;
        this.paymentClient = paymentClient;
    }

    @Transactional
    public Long createOrder(OrderRequest orderRequest) {
        //todo check the customer -customer MS - openFeign
        var customer = customerClient.findCustomerById(orderRequest.customerId())
                .orElseThrow(() -> new BusinessException("Cannot create order :: no customer found !"));

        //todo purchase the products -Product MS- RestTemplate
        var purchasedProduct = productClient.purchaseProducts(orderRequest.products());

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
        //todo start payment process with notification payment (kafka)
        paymentClient.createPayment(PaymentRequest.builder()
                        .paymentMethod(orderRequest.paymentMethod())
                        .orderId(orderRequest.id())
                        .customer(customer)
                        .orderReference(orderRequest.reference())
                        .amount(orderRequest.totalAmount())
                .build());

        //todo send the order confirmation -- notification ms (kafka)
        kafkaOrderProducer.sendOrderConfirmation(OrderConfirmation
                .builder()
                        .reference(orderRequest.reference())
                        .paymentMethod(orderRequest.paymentMethod())
                        .totalAmount(orderRequest.totalAmount())
                        .customerResponse(customer)
                        .products(purchasedProduct)
                .build()
        );

        return order.getId();
    }

    public List<OrderResponse> findAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(orderMapper::toOrderResponse).toList();
    }

    public OrderResponse findOrderById(Long id) {
        return orderRepository.findById(id).map(orderMapper::toOrderResponse).orElseThrow(()->
            new BusinessException("Order Id not found. try again!")
        );
    }
}
