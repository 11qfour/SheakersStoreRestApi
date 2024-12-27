package com.example.demo.DTO.mapper;

import com.example.demo.DTO.SneakersDtoRequest;
import com.example.demo.DTO.SneakersDtoResponse;
import com.example.demo.model.Sneakers;
import com.example.demo.model.Supplier;
import org.mapstruct.*;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, //указывает, что интерфейс будет использоваться для преобразования данных между объектами.
        componentModel = MappingConstants.ComponentModel.SPRING)
public interface SneakersMapper {
    Sneakers toEntity(SneakersDtoRequest sneakersDtoRequest);

    @Mapping(target = "supplierIds", expression = "java(suppliersToSupplierIds(sneakers.getSuppliers()))")  // используется для определения специфичных правил сопоставления между полями источника и назначения
    @Mapping(target = "sneakersTypeId", source = "sneakersType.id")
    SneakersDtoResponse toDto(Sneakers sneakers); //(аннотации) извлечение идентификаторов поставщиков и идентификатора типа кроссовок

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE) // в случае, если источник имеет значение null, соответствующее целевое поле не будет обновлено
    Sneakers partialUpdate(SneakersDtoRequest sneakersDtoRequest, @MappingTarget Sneakers sneakers); //параметр метода будет использоваться как целевой объект для маппинга

    default Set<Long> suppliersToSupplierIds(Set<Supplier> suppliers) { //преобразует набор поставщиков
        return suppliers.stream().map(Supplier::getId).collect(Collectors.toSet()); //в набор их ID
    }
}
