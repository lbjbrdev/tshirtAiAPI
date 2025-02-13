package com.example.shirts.adapters.api.controller;

import com.example.shirts.adapters.persistence.entity.Shirt;
import com.example.shirts.application.service.ShirtService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/shirts")
public class ShirtController {
    private final ShirtService shirtService;

    public ShirtController(ShirtService shirtService) {
        this.shirtService = shirtService;
    } 

    @GetMapping
    public List<Shirt> getAllShirts() {
        return shirtService.getAllShirts();
    }

    @PostMapping
    public Shirt addShirt(@RequestBody Shirt shirt) {
        return shirtService.addShirt(shirt);
    }

    @GetMapping("/{id}")
    public Optional<Shirt> getShirtById(@PathVariable Long id) {
        return shirtService.getShirtById(id);
    }
}