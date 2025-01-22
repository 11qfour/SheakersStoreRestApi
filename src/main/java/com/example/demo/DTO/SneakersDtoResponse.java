package com.example.demo.DTO;

import com.example.demo.model.Sneakers;
import lombok.Value;

import java.util.Set;
@Value //поля становятся final
public class SneakersDtoResponse {
    String sneakerModel;
    double size;
    int price;
    Long sneakersTypeId;
    Set<Long> supplierIds;
}
