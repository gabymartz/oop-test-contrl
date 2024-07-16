package com.ups.oop.dto;

public class AnimalsDTO {
    private String id;
    private String name;
    private String breath;
    private String color;
    private double weight;
    private double height;
    private double length;

    public AnimalsDTO(){
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getBreath() {
        return breath;
    }
    public void setBreath(String breath) {
        this.breath = breath;
    }

    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }

    public double getWeight() {
        return weight;
    }
    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }
    public void setHeight(double height) {
        this.height = height;
    }

    public double getLength() {
        return length;
    }
    public void setLength(double length) {
        this.length = length;
    }
    @Override
    public String toString() {
        return "AnimalsDTO{" +
                "id='" + id + '\'' +
                "name='" + name + '\'' +
                ", breath='" + breath + '\'' +
                ", color='" + color + '\'' +
                ", weight=" + weight +'\'' +
                ", height=" + height +'\'' +
                ", length=" + length +
                '}';
    }
}
