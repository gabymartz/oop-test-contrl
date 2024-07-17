package com.ups.oop.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter

public class AnimalsDTO {
    private String id;
    private double length;
    private double height;
    private double weight;
    private String color;
    private String breed;
    private String name;
}