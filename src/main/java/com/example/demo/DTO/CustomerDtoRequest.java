package com.example.demo.DTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Value;

@Value
public class CustomerDtoRequest {
    @NotBlank(message = "Фамилия не должна быть пустой")
    String firstName;
    @NotBlank(message = "Имя не должно быть пустым")
    String lastName;
    String patronymic;
    @Valid
    ContactInfoDto contactInfo;
}
