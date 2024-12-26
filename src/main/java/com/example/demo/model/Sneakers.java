package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import javax.annotation.processing.Generated;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity //указывает на то что сущность из Бд (JPA)
public class Sneakers {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE) //функция последовательности для генгерации данного PK
    private Long id;

    private String sneakerModel;
    private String type;
}
