package com.example.demo.controller;

import com.example.demo.DTO.SneakersTypeDtoRequest;
import com.example.demo.DTO.SneakersTypeDtoResponse;
import com.example.demo.service.SneakersTypeService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true)
@RequestMapping("/api/sneakers/type")
public class SneakersTypeController {
    private SneakersTypeService sneakersTypeService;
    @GetMapping
    public List<SneakersTypeDtoResponse> getAllSneakersType(){
        return sneakersTypeService.getAllTypeSneackers();
    }

    @GetMapping("/{id}")
    public SneakersTypeDtoResponse  getSneakersTypeById(@PathVariable Long id){
        return sneakersTypeService.getSneakersTypeById(id); //200
    }

    @PostMapping
    private SneakersTypeDtoResponse createSneakersType(@RequestBody SneakersTypeDtoRequest sneakersTypeDtoRequest){
        return sneakersTypeService.create(sneakersTypeDtoRequest);
    }

    @PutMapping("/{id}")
    private SneakersTypeDtoResponse updateSneakersType(@PathVariable Long id,@RequestBody SneakersTypeDtoRequest sneakersTypeDt){
        return sneakersTypeService.updateSneakersType(id,sneakersTypeDt);
    }

    @DeleteMapping ("/{id}")
    private void deleteSneakers(@PathVariable Long id){
        sneakersTypeService.deleteSneakersType(id);
    }
}
