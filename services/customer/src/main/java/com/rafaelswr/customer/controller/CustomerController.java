package com.rafaelswr.customer.controller;

import com.rafaelswr.customer.customer.CustomerRequestDTO;
import com.rafaelswr.customer.customer.CustomerResponseDTO;
import com.rafaelswr.customer.service.CustomerService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponseDTO> getCustomerById(@PathVariable String id){
        return new ResponseEntity<>(customerService.getCustomerById(id), HttpStatus.OK);
    }

    @GetMapping("/exists/{id}")
    public ResponseEntity<Boolean> existsById(@PathVariable String id) {
        return new ResponseEntity<>(customerService.existsById(id), HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<List<CustomerResponseDTO>> getAllCustomers(){
        return new ResponseEntity<>(customerService.getAllCustomers(), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<String> createCustomer(@Valid @RequestBody CustomerRequestDTO customerRequestDTO){
        return new ResponseEntity<>(customerService.saveNewCustomer(customerRequestDTO), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public void updateCustomer(@Valid @RequestBody CustomerRequestDTO customerRequestDTO){
     customerService.updateCustomer(customerRequestDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteDocById(@PathVariable String id){
        return new ResponseEntity<>(customerService.deleteById(id), HttpStatus.OK);
    }

}
