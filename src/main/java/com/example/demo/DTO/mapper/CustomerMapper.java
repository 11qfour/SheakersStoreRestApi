package com.example.demo.DTO.mapper;

import com.example.demo.DTO.CustomerDtoRequest;
import com.example.demo.DTO.CustomerDtoResponse;
import com.example.demo.model.ContactInfo;
import com.example.demo.model.Customer;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING)
public interface CustomerMapper {
    Customer toEntity(CustomerDtoRequest customerDtoRequest);

    @AfterMapping
    default void linkContactInfo(@MappingTarget Customer customer) { //устанавливает ссылку на Customer
        ContactInfo contactInfo = customer.getContactInfo(); //в ContactInfo (if exist)
        if (contactInfo != null) {
            contactInfo.setCustomer(customer);
        }
    }

    @Mapping(target = "contactInfo", source = "contactInfo")
    CustomerDtoResponse toDto(Customer customer);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Customer partialUpdate(CustomerDtoRequest customerDtoRequest, @MappingTarget Customer customer);
}
