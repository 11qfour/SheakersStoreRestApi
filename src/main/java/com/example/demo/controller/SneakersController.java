package com.example.demo.controller;

import com.example.demo.DTO.SneakersDtoRequest;
import com.example.demo.DTO.SneakersDtoResponse;
import com.example.demo.service.SneakersService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true)
@RequestMapping("/api/sneakers")
public class SneakersController {
    private SneakersService sneakersService;

    @GetMapping
    public List<SneakersDtoResponse> getAllSneakers(){
        return sneakersService.getAllSneakers();
    }

    @GetMapping("/{id}")
    public SneakersDtoResponse getSneakersById(@PathVariable Long id){
        return sneakersService.getSneakersById(id); //200
    }

    @PostMapping
    private SneakersDtoResponse createSneakers(@Valid @RequestBody SneakersDtoRequest sneakersDto){
        return sneakersService.create(sneakersDto);//201
    }

    @PutMapping("/{id}")
    private SneakersDtoResponse updateSneakers(@PathVariable Long id,@Valid @RequestBody SneakersDtoRequest sneakersDto){
        return sneakersService.updateSneakers(id,sneakersDto);
    }

    @DeleteMapping ("/{id}")
    private void deleteSneakers(@PathVariable Long id){ //пафвариабл позволяет извлечь значение айди
        sneakersService.deleteSneakers(id);
    }

    @PostMapping("/addRelation")
    public ResponseEntity<String> addSneakerSupplierRelation(@RequestParam Long sneakersId, @RequestParam Long supplierId) {
        sneakersService.addSneakerAndSupplierRelation(sneakersId, supplierId);
        return ResponseEntity.ok("Relation added successfully");
    }
}
