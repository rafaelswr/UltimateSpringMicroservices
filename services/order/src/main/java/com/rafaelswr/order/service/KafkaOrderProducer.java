package com.rafaelswr.order.service;

import com.rafaelswr.order.kafka.OrderConfirmation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaOrderProducer {

        private final KafkaTemplate<String, OrderConfirmation> kafkaTemplate;

        public void sendOrderConfirmation(OrderConfirmation orderConfirmation){
                log.info("Sending order confirmation...");
                Message<OrderConfirmation> message = MessageBuilder
                        .withPayload(orderConfirmation)
                        .setHeader(KafkaHeaders.TOPIC, "orderNotification-topic")
                        .build();
                log.info("Order Confirmation Object: " + message);
                kafkaTemplate.send(message);

        }



}
