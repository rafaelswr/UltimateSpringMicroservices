package com.rafaelswr.paymentservice.controller;

import com.rafaelswr.paymentservice.payment.Payment;
import com.rafaelswr.paymentservice.payment.PaymentRequest;
import com.rafaelswr.paymentservice.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping()
    public ResponseEntity<Long> createPayment(@RequestBody @Valid PaymentRequest paymentRequest){
        return new ResponseEntity<>(paymentService.createOrderPayment(paymentRequest), HttpStatus.CREATED);
    }

}
