package com.rafaelswr.customer.service;

import com.ctc.wstx.util.StringUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rafaelswr.customer.customer.Customer;
import com.rafaelswr.customer.customer.Customer.CustomerBuilder;
import com.rafaelswr.customer.customer.CustomerRequestDTO;
import com.rafaelswr.customer.customer.CustomerResponseDTO;
import com.rafaelswr.customer.exception.CustomerNotFoundException;
import com.rafaelswr.customer.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.AccessType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Slf4j
@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerResponseDTO getCustomerById(String id){
        return customerRepository.findByDocId(id)
                .map(this::CustomerToResponseDTO)
                .orElseThrow(() ->
                new CustomerNotFoundException("Not Found CUSTOMER on getByID"));
    }

    public List<CustomerResponseDTO> getAllCustomers() {
        return customerRepository.findAll().stream().map(this::CustomerToResponseDTO).collect(Collectors.toList());
    }

    public String saveNewCustomer(CustomerRequestDTO customerDTO){
        Customer c = DtoToCustomer(customerDTO);
        customerRepository.save(DtoToCustomer(customerDTO));
        log.info(c.getId());
        return c.getId();
    }

    public void updateCustomer(CustomerRequestDTO customerRequestDTO) {
        var c = customerRepository.findByDocId(customerRequestDTO.id()).orElseThrow(()->
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
        if(customerRequestDTO.address()!=null) {
            c.setAddress(customerRequestDTO.address());
        }
    }

    private CustomerResponseDTO CustomerToResponseDTO(Customer c){
        return CustomerResponseDTO.builder().email(c.getEmail()).lastname(c.getLastName())
                .firstName(c.getFirstName()).build();
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

    public Boolean existsById(String id) {
        return customerRepository.findByDocId(id).isPresent();
    }

    public String deleteById(String id) {
       customerRepository.deleteById(id);
       return format("Deleted "+ id);
    }
}
