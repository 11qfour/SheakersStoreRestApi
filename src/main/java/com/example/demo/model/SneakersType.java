package com.example.demo.model;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "sneakers_type")
public class SneakersType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @Column(nullable = true)
    private String description;

    @OneToMany(mappedBy = "sneakersType", cascade = CascadeType.ALL) //mappedBy - сторона, которая содержит связь, находится в классе Sneakers
    private Set<Sneakers> sneakers = new HashSet<>();

    @Override
    public final boolean equals(Object object) { //сравнение двух объектов по id и классам
        if (this == object) return true;
        if (object == null) return false;
        Class<?> oEffectiveClass = object instanceof HibernateProxy proxy ? proxy.getHibernateLazyInitializer().getPersistentClass() : object.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy proxy ? proxy.getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        SneakersType category = (SneakersType) object;
        return getId() != null && Objects.equals(getId(), category.getId());
    }

    @Override
    public final int hashCode() { //вычисление хэш-кода для хранения объектов в хэш-структурах данных
        return this instanceof HibernateProxy proxy ? proxy.getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
