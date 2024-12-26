package com.example.demo.controller;

import com.example.demo.DTO.CustomerDtoResponse;
import com.example.demo.DTO.SneakersDtoResponse;
import com.example.demo.service.CustomerService;
import com.example.demo.service.SneakersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
/*@RequestMapping("/sneakers")*/
public class ViewController {
    private final SneakersService sneakerService;
    private final CustomerService customerService;

    @GetMapping("/view/sneakers")
    public String viewProducts(Model model) {
        List<SneakersDtoResponse> sneakers = sneakerService.getAllSneakers();
        model.addAttribute("sneakers", sneakers);
        return "sneakers";
    }

    @GetMapping("/view/customers")
    public String viewCustomers(Model model) {
        List<CustomerDtoResponse> customers = customerService.findAll();
        model.addAttribute("customers", customers);
        return "customers";
    }
}
