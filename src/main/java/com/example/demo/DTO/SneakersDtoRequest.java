package com.example.demo.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Value;

import java.util.Set;
@Value
public class SneakersDtoRequest {
    @NotBlank (message = "Модель не может быть пустой")//не является нулем
    String sneakerModel;
    @Positive (message = "Размер должен быть положительным")//>0
    @NotNull(message = "Размер не должен быть пустым")
    double size;
    @Positive(message = "Цена должна быть положительной")
    @NotNull (message = "Цена не должна быть пустой")
    int price;
    Long sneakersTypeId;
    Set<Long> supplierIds;
}
