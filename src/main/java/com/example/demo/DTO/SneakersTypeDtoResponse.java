package com.example.demo.DTO;

import com.example.demo.model.Sneakers;
import lombok.Value;

import java.util.Set;

@Value
public class SneakersTypeDtoResponse {
    String name;
    String description;
    Set<Sneakers> sneakers;
}
