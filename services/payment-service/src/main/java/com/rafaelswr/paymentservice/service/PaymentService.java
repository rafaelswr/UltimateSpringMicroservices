package com.rafaelswr.paymentservice.service;

import com.rafaelswr.paymentservice.payment.PaymentRequest;
import com.rafaelswr.paymentservice.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Long createOrderPayment(PaymentRequest paymentRequest) {

    paymentRepository.save()
        return ;
    }
}
