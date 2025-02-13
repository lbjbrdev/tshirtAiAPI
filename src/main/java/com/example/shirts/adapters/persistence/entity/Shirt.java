package com.example.shirts.adapters.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "shirts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Shirt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private String color;
    private String club;
    private String size;
    private int year;
    private String model;
    private double purchasePrice;
    private double marketValue;
    private String imageUrl;
}