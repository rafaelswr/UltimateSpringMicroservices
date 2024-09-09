package com.rafaelswr.customer.service;

import com.ctc.wstx.util.StringUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rafaelswr.customer.customer.Customer;
import com.rafaelswr.customer.customer.Customer.CustomerBuilder;
import com.rafaelswr.customer.customer.CustomerRequestDTO;
import com.rafaelswr.customer.exception.CustomerNotFoundException;
import com.rafaelswr.customer.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.AccessType;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Optional<Customer> getCustomerById(String id){
        return Optional.ofNullable(customerRepository.findByDocId(id).orElseThrow(() -> new CustomerNotFoundException("Not FOund USER")));
    }

    public String saveNewCustomer(CustomerRequestDTO customerDTO){
        Customer c = DtoToCustomer(customerDTO);
        customerRepository.save(DtoToCustomer(customerDTO));
        log.info(c.getId());
        return c.getId();
    }



    private Customer DtoToCustomer(CustomerRequestDTO customerDTO){
        return Customer.builder()
                .id(customerDTO.id())
                .email(customerDTO.email())
                .firstName(customerDTO.firstName())
                .lastName(customerDTO.lastName())
                .phone(customerDTO.phone())
                .address(customerDTO.address())
                .build();
    }

    public void updateCustomer(CustomerRequestDTO customerRequestDTO) {
        var c = customerRepository.findByDocId("66df7521a07b021ff2f7fa47").orElseThrow(()->
                new CustomerNotFoundException("Cannot update Customer by ID, NOT FOUND! ")
        );
       mergeCustomer(c, customerRequestDTO);
       customerRepository.save(c);
    }

    private void mergeCustomer(Customer c, CustomerRequestDTO customerRequestDTO) {

        if(StringUtils.isNotBlank(customerRequestDTO.firstName())){
            c.setFirstName(customerRequestDTO.firstName());
        }
        if(StringUtils.isNotBlank(customerRequestDTO.lastName())){
            c.setLastName(customerRequestDTO.lastName());
        }
        if(StringUtils.isNotBlank(customerRequestDTO.email())){
            c.setEmail(customerRequestDTO.email());
        }
        if(customerRequestDTO.phone() != null){
            c.setPhone(customerRequestDTO.phone());
        }
        if(customerRequestDTO.address()!=null){
            c.setAddress(customerRequestDTO.address());
        }


    }

}
