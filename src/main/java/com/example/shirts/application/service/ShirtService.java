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

    public Shirt addShirt(Shirt shirt, MultipartFile imageFile) throws IOException {
        Optional<Shirt> existingShirt = shirtRepository.findByBrandAndModelAndYear(
            shirt.getBrand(),
            shirt.getModel(),
            shirt.getYear().
        );

        if (existingShirt.isPresent()) {
            throw new RuntimeException("Shirt already exists in the collection!");
        }

        Shirt savedShirt = shirtRepository.save(shirt);

        kafkaProducer.sendShirtEvent("New shirt added: " + savedShirt.getBrand() + " - " + savedShirt.getModel());

        String description = String.format("%s %s %s %s %s", 
            shirt.getBrand(), 
            shirt.getModel(), 
            shirt.getYear(), 
            shirt.getSize(), 
            shirt.getColor()
        );

        boolean isDuplicate = OpenAIService.isShirtDuplicate(description);

        if (isDuplicate) {
            throw new RuntimeException("This shirt is already in the collection!");
        }

        String imageUrl = s3Service.uploadFile(imageFile);

        shirt.setImageUrl(imageUrl);

        Shirt savedShirt = shirtRepository.save(shirt);

        kafkaProducer.sendShirtEvent("New shirt added: " + savedShirt.getBrand() + " - " + savedShirt.getModel());

        return savedShirt;
    }
}