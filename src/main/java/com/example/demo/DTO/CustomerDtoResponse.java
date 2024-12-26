package com.example.demo.DTO;

import lombok.Value;

@Value
public class CustomerDtoResponse {
    String firstName;
    String lastName;
    String patronymic;
    ContactInfoDto contactInfo;
}
