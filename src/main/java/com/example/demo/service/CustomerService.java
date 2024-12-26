package com.example.demo.service;

import com.example.demo.DTO.CustomerDtoRequest;
import com.example.demo.DTO.CustomerDtoResponse;
import com.example.demo.DTO.mapper.CustomerMapper;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerDtoResponse createCustomer(CustomerDtoRequest customerDto) {
        Customer customer = customerMapper.toEntity(customerDto);
        Customer savedCustomer = customerRepository.save(customer);
        return customerMapper.toDto(savedCustomer);
    }

    public CustomerDtoResponse findById(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Не существует клиента с таким id: " + id));
        return customerMapper.toDto(customer);
    }

    public List<CustomerDtoResponse> findAll() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream()
                .map(customerMapper::toDto)
                .collect(Collectors.toList());
    }

    public CustomerDtoResponse updateCustomer(Long id, CustomerDtoRequest customerDetails) {
        Customer customer = customerRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Не существует клиента с таким id: " + id));

        customerMapper.partialUpdate(customerDetails, customer);

        return customerMapper.toDto(customerRepository.save(customer));
    }

    public void deleteById(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new ResourceNotFoundException("Не существует клиента с таким id: " + id);
        }
        customerRepository.deleteById(id);
    }
}
