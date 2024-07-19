package com.ups.oop.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Animals {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String animalsCode;
    private String name;
    private String breed;
    private String color;
    private double weight;
    private double height;
    private double length;
}
