package com.rafaelswr.paymentservice.service;

import com.rafaelswr.paymentservice.kafka.PaymentNotification;
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
public class KafkaPaymentConfirmation {

    private final KafkaTemplate<String, PaymentNotification> kafkaTemplate;

    public void sendPaymentConfirmation(PaymentNotification paymentNotification){
        log.info("Sending Payment Confirmation with body <{}>....", paymentNotification);
        Message<PaymentNotification> message = MessageBuilder
                .withPayload(paymentNotification)
                .setHeader(KafkaHeaders.TOPIC, "payment-confirmation")
                .build();
        kafkaTemplate.send(message);
    }

}
