package com.rafaelswr.customer.controller;

import com.rafaelswr.customer.customer.Customer;
import com.rafaelswr.customer.customer.CustomerRequestDTO;
import com.rafaelswr.customer.service.CustomerService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/customer")
public class CustomController {

    private final CustomerService customerService;

    @Autowired
    public CustomController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Customer>> getCustomerById(@PathVariable String id){
        return new ResponseEntity<>(customerService.getCustomerById(id), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<String> createCustomer(@Valid @RequestBody CustomerRequestDTO customerRequestDTO){
        return new ResponseEntity<>(customerService.saveNewCustomer(customerRequestDTO), HttpStatus.CREATED);
    }

    @PutMapping("/a")
    public void updateCustomer(@Valid @RequestBody CustomerRequestDTO customerRequestDTO){
     customerService.updateCustomer(customerRequestDTO);
    }

}
