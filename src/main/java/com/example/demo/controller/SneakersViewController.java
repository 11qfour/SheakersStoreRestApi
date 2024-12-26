package com.example.demo.controller;

import com.example.demo.model.Sneakers;
import com.example.demo.service.SneakersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/sneakers")
public class SneakersViewController {
    private final SneakersService sneakersService;

    @GetMapping
    public String getAllSneakers(Model model){
        model.addAttribute("sneakers", sneakersService.findAll());
        return "sneakers";
    }

    @GetMapping("/create")
    public String showCreateForm(){
        return "create-sneaker";
    }

    @PostMapping
    public String createSneakers(@ModelAttribute Sneakers sneakers){
        sneakersService.create(sneakers);
        return "redirect:/sneakers";
    }

    @GetMapping("/{id}")
    public String getSneakers(@PathVariable Long id, Model model){
        model.addAttribute("sneakers", sneakersService.findById(id));
        return "edit-sneakers";
    }

    @PostMapping("/{id}")
    public String updateSneakers(@PathVariable Long id, @ModelAttribute Sneakers sneakersDetails){
        sneakersService.update(id, sneakersDetails);
        return "redirect:/sneakers";
    }

    @PostMapping("{id}/delete")
    public String deleteSneakers(@PathVariable Long id){
        sneakersService.delete(id);
        return "redirect:/sneakers";
    }
}
