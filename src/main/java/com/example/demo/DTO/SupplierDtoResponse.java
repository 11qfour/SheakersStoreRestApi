package com.example.demo.DTO;

import com.example.demo.model.Sneakers;
import lombok.Value;

import java.util.Set;

@Value
public class SupplierDtoResponse {
    String name;
    String contactNumber;
    Set<Sneakers> sneakers;
}
