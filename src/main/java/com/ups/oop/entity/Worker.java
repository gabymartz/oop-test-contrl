package com.ups.oop.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
 public class Worker extends Person {
    private String workerCode;

    public Worker() {
        super();
    }

    public Worker(String workerCode, String personId, String name, String lastname, Integer age) {
        super(personId, name, lastname, age);
        this.workerCode = workerCode;
    }
}