package com.example.demo.DTO.mapper;

import com.example.demo.DTO.ContactInfoDto;
import com.example.demo.model.ContactInfo;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING)
public interface ContactInfoMapper {
    ContactInfo toEntity(ContactInfoDto contactInfoDto); //преобразование DTO в ContactInfo

    ContactInfoDto toDto(ContactInfo contactInfo); //обратное преобразование

    //BeanMapping для игнорирования пустых значений при маппинге
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ContactInfo partialUpdate(ContactInfoDto contactInfoDto, //частичное обновление ContactInfo
                              @MappingTarget ContactInfo contactInfo);
}
