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
    private String animalsCode;// name-breed-color
    private double weight;
    private double height;
    private double length;
}