package com.example.demo.DTO;

import com.example.demo.model.Sneakers;
import jakarta.validation.constraints.NotBlank;
import lombok.Value;

import java.util.Set;

@Value
public class SupplierDtoRequest {
    @NotBlank
    String name;
    String contactNumber;
    Set<Sneakers> sneakers;
}
