package com.example.shirts.adapters.persistence.repository;

import com.example.shirts.adapters.persistence.entity.Shirt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.sterotype.Repository;

import java.util.Optional;

@Repositorypublic interface ShirtRepository extends JpaRepository<Shirt, Long> {
    Optional<Shirt> findByBrandAndModelAndYear(String brand, String model, int year);
}