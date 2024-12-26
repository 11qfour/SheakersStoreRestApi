package com.example.demo.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Positive;
import lombok.Value;

@Value
public class ContactInfoDto {
    @Email(message = "Email должен быть действительным")
    String email;
    String phone;
    @Positive(message = "Возраст должен быть положительным")
    int age;
}
