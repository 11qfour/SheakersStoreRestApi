package com.example.demo.DTO.mapper;

import com.example.demo.DTO.SupplierDtoRequest;
import com.example.demo.DTO.SupplierDtoResponse;
import com.example.demo.model.Sneakers;
import com.example.demo.model.Supplier;
import org.mapstruct.*;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, //указывает, что интерфейс будет использоваться для преобразования данных между объектами.
        componentModel = MappingConstants.ComponentModel.SPRING)
public interface SupplierMapper {
    Supplier toEntity(SupplierDtoRequest supplierDtoRequest);

    @Mapping(target="sneakers", expression="java(sneakersToSneakers(supplier.getSneakers()))")
    SupplierDtoResponse toDto(Supplier supplier);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Supplier partialUpdate(SupplierDtoRequest supplierDtoRequest, @MappingTarget Supplier supplier);

    default Set<Sneakers> sneakersToSneakers(Set<Sneakers> sneakers) {
        /*return sneakers.stream().map(Sneakers).collect(Collectors.toSet());*/
        return new HashSet<>(sneakers);
    }
}
