package com.rafaelswr.order.payment;

import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@FeignClient(
        name = "payment",
        url = "${application.config.payment-service}"
)
public interface PaymentClient {

    @PostMapping()
    Optional<Long> createPayment(@RequestBody @Valid PaymentRequest paymentRequest);


}
