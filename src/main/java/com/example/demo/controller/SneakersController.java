package com.example.demo.controller;

import com.example.demo.model.Sneakers;
import com.example.demo.service.SneakersService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true)
@RequestMapping("/api/sneakers")
public class SneakersController {
    private SneakersService sneakersService;

    @GetMapping
    public ResponseEntity<List<Sneakers>> getAllSneakers(){
        return ResponseEntity.status(HttpStatus.OK).body(sneakersService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sneakers> getSneakersById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(sneakersService.findById(id)); //200
    }

    @PostMapping
    private ResponseEntity<Sneakers> createSneakers(@RequestBody Sneakers sneakers){
        return ResponseEntity.status(HttpStatus.CREATED).body(sneakersService.create(sneakers));//201
    }

    @PutMapping("/{id}")
    private ResponseEntity<Sneakers> updateSneakers(@PathVariable Long id, @RequestBody Sneakers sneakers){
        return ResponseEntity.status(HttpStatus.OK).body(sneakersService.update(id,sneakers));
    }

    @DeleteMapping ("/{id}")
    private ResponseEntity<Void> deleteSneakers(@PathVariable Long id){ //пафвариабл позволяет извлечь значение айди
        sneakersService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); //204 успешный запрос без содержимого
    }
}
