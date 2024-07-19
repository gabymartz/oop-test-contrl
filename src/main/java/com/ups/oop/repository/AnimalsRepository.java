package com.ups.oop.repository;

import com.ups.oop.entity.Animals;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AnimalsRepository extends CrudRepository<Animals, Long> {
}
