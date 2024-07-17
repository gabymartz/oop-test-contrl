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
    private double length;
    private double height;
    private double weight;
    private String color;
    private String breed;
    private String name;
}
