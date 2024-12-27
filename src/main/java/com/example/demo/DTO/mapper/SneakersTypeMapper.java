package com.example.demo.DTO.mapper;

import com.example.demo.DTO.SneakersDtoRequest;
import com.example.demo.DTO.SneakersTypeDtoRequest;
import com.example.demo.DTO.SneakersTypeDtoResponse;
import com.example.demo.model.Sneakers;
import com.example.demo.model.SneakersType;
import com.example.demo.model.Supplier;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING)
public interface SneakersTypeMapper {
        SneakersType toEntity(SneakersTypeDtoRequest sneakersTypeDtoRequest);
        @Mapping(target = "sneakers", source = "sneakers") // Теперь мы сопоставляем полные объекты Sneakers
        SneakersTypeDtoResponse toDto(SneakersType sneakersType);

        @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
        SneakersType partialUpdate(SneakersTypeDtoRequest sneakersTypeDtoRequest, @MappingTarget SneakersType sneakersType);

        // Преобразование идентификаторов кроссовок в объекты Sneakers
        default Set<Sneakers> map(Set<Long> sneakerIds) {
                return sneakerIds.stream()
                        .map(sneakersId -> {
                                Sneakers sneakers = new Sneakers();
                                sneakers.setId(sneakersId); // Создайте объект Sneakers с только id
                                return sneakers;
                        })
                        .collect(Collectors.toSet());
        }
}
