package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="suppliers")
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String contactNumber;

    @ManyToMany
    @JoinTable(name = "suppliers",
            joinColumns = @JoinColumn(name = "supplier_id"), //supplier_id - внешний ключ для Supplier
            inverseJoinColumns = @JoinColumn(name = "sneakers_id")) //sneakers_id - внешний ключ для Product
    private Set<Sneakers> sneakers = new HashSet<>();

    @Override
    public final boolean equals(Object object) { //сравнение двух объектов по id и классам
        if (this == object) return true;
        if (object == null) return false;
        Class<?> oEffectiveClass = object instanceof HibernateProxy proxy ? proxy.getHibernateLazyInitializer().getPersistentClass() : object.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy proxy ? proxy.getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Supplier supplier = (Supplier) object;
        return getId() != null && Objects.equals(getId(), supplier.getId());
    }

    @Override
    public final int hashCode() { //вычисление хэш-кода для хранения объектов в хэш-структурах данных
        return this instanceof HibernateProxy proxy ? proxy.getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
