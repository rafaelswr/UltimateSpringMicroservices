package com.rafaelswr.order.customer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(
        name = "customer",
        url = "http://localhost:8099/customer"
)
public interface CustomerClient {

    @GetMapping("/{id}")
    Optional<CustomerResponse> findCustomerById(@PathVariable("id") String customerId);

}
