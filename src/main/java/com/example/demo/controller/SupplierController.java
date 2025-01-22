package com.example.demo.controller;

import com.example.demo.DTO.SneakersDtoRequest;
import com.example.demo.DTO.SneakersDtoResponse;
import com.example.demo.DTO.SupplierDtoRequest;
import com.example.demo.DTO.SupplierDtoResponse;
import com.example.demo.service.SupplierService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true)
@RequestMapping("/api/supplier")
public class SupplierController {
    private SupplierService supplierService;

    @GetMapping
    public List<SupplierDtoResponse> getAllSupplier(){return supplierService.getAllSupplier();}

    @GetMapping("/{id}")
    public SupplierDtoResponse getSuppliersById(@PathVariable Long id){
        return supplierService.getSupplierById(id);
    }

    @PostMapping
    private SupplierDtoResponse createSneakers(@Valid @RequestBody SupplierDtoRequest supplierDtoRequest){
        return supplierService.create(supplierDtoRequest);//201
    }

    @PutMapping("/{id}")
    private SupplierDtoResponse updateSneakers(@PathVariable Long id,@Valid @RequestBody SupplierDtoRequest supplierDtoRequest){
        return supplierService.updateSneakers(id,supplierDtoRequest);
    }

    @DeleteMapping ("/{id}")
    private void deleteSupplier(@PathVariable Long id){ //пафвариабл позволяет извлечь значение айди
        supplierService.deleteSupplier(id);
    }
}
