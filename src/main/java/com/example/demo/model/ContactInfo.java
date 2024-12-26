package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "contact_infos")
public class ContactInfo {
    @Id
    private Long id;
    @Column(nullable = true)
    private String email;
    private String phone;
    private int age;
    private int numberIP;

    @OneToOne(fetch = FetchType.LAZY) //fetch- contact-info загружается при необходимости
    @MapsId
    private Customer customer;
}
