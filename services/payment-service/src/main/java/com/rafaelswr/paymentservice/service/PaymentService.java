package com.rafaelswr.paymentservice.service;

import com.rafaelswr.paymentservice.kafka.PaymentNotification;
import com.rafaelswr.paymentservice.payment.PaymentMapper;
import com.rafaelswr.paymentservice.payment.PaymentRequest;
import com.rafaelswr.paymentservice.payment.Status;
import com.rafaelswr.paymentservice.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final KafkaPaymentConfirmation kafkaPaymentConfirmation;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository, PaymentMapper paymentMapper, KafkaPaymentConfirmation kafkaPaymentConfirmation) {
        this.paymentRepository = paymentRepository;
        this.paymentMapper = paymentMapper;
        this.kafkaPaymentConfirmation = kafkaPaymentConfirmation;
    }

    public Long createOrderPayment(PaymentRequest paymentRequest) {
        var paymentID =  paymentRepository.save(paymentMapper.toPayment(paymentRequest)).getId();
        kafkaPaymentConfirmation.sendPaymentConfirmation(
                new PaymentNotification(
                        paymentRequest.customer().firstName(),
                        paymentRequest.customer().lastName(),
                        paymentRequest.customer().email(),
                        Status.AWAITING_PAYMENT,
                        paymentRequest.amount(),
                        paymentRequest.orderReference(),
                        paymentRequest.paymentMethod()
                )
        );
        return paymentID;
    }
}
