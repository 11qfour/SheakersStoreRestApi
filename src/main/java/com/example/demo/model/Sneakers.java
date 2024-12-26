package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import javax.annotation.processing.Generated;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity //указывает на то что сущность из Бд (JPA)
@Table(name = "sneakers")
public class Sneakers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //единственный
    @Column(nullable = false)
    private Long id;

    private String sneakerModel;
    private double size;
    private int price;

    @ManyToMany(mappedBy = "sneakers", cascade = {CascadeType.PERSIST, CascadeType.MERGE}) //каскадное сохранение и обновление
    private Set<Supplier> suppliers = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "sneakersType_id") //sneakersType_id - внешний ключ для связи с sneakersType
    private SneakersType sneakersType;

    @Override
    public final boolean equals(Object object) {//сравнение двух объектов по id и классам
        if (this == object) return true;
        if (object == null) return false;
        Class<?> oEffectiveClass = object instanceof HibernateProxy proxy ? proxy.getHibernateLazyInitializer().getPersistentClass() : object.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy proxy ? proxy.getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Sneakers product = (Sneakers) object;
        return getId() != null && Objects.equals(getId(), product.getId());
    }

    @Override
    public final int hashCode() {//вычисление хэш-кода для хранения объектов в хэш-структурах данных
        return this instanceof HibernateProxy proxy ? proxy.getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
