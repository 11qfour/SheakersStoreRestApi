package com.example.demo.controller;

import com.example.demo.DTO.CustomerDtoRequest;
import com.example.demo.DTO.CustomerDtoResponse;
import com.example.demo.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<CustomerDtoResponse>> getAllCustomers() {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDtoResponse> getCustomerById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.findById(id));
    }

    @PostMapping
    public ResponseEntity<CustomerDtoResponse> createCustomer(@Valid @RequestBody CustomerDtoRequest customerDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.createCustomer(customerDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDtoResponse> updateCustomer(@PathVariable Long id, @Valid @RequestBody CustomerDtoRequest customerDto) {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.updateCustomer(id, customerDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
