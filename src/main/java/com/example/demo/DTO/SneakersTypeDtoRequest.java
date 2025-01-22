package com.example.demo.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Value;

import java.util.Set;

@Value
public class SneakersTypeDtoRequest {
    @NotBlank(message = "Тип кроссовок не может быть пустым")
    String name;
    String description;
    Set<Long> sneakers;
}
