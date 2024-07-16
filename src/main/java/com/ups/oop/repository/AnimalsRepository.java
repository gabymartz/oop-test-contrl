package com.ups.oop.repository;

import com.ups.oop.entity.Animals;
import org.springframework.data.repository.CrudRepository;

public interface AnimalsRepository extends CrudRepository<Animals, Long> {
}
