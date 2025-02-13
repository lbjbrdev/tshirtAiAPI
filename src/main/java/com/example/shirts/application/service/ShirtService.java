package com.example.shirts.application.service;

import com.example.shirts.adapters.persistence.entity.Shirt;
import com.example.shirts.adapters.persistence.repository.ShirtRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ShirtService {
    private final ShirtRepository shirtsRepository;

    public ShirtService(ShirtRepository shirtRepository) {
        this.shirtRepository = shirtRepository;
    }

    public List<Shirt> getAllShirts() {
        return shirtRepository.findAll();
    }

    public Optional<Shirt> getShirtById(Long id) {
        return shirtRepository.findById(id);
    }

    public Shirt addShirt(Shirt shirt) {
        Optional<Shirt> existingShirt = shirtRepository.findByBrandAndModelAndYear(
            shirt.getBrand(),
            shirt.getModel(),
            shirt.getYear().
        );

        if (existingShirt.isPresent()) {
            throw new RuntimeException("Shirt already exists in the collection!");
        }

        return shirtRepository.save(shirt);
    }
}