package com.ups.oop.controller;

import com.ups.oop.dto.AnimalsDTO;
import com.ups.oop.service.AnimalsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RestController

public class AnimalsController {
    private final AnimalsService animalsService;

    public AnimalsController(AnimalsService animalsService){
        this.animalsService= animalsService;
    }
    @GetMapping("/get-all-animals")
    public ResponseEntity getAllAnimals() {
        return this.animalsService.getAllAnimals();
    }
    @GetMapping("/get-animals")
    public ResponseEntity getAnimalsById(@RequestParam String id) {
        return this.animalsService.getAnimalsById(id);
    }

    @PostMapping("/animalsDTO")
    public ResponseEntity createAnimals(@RequestBody AnimalsDTO animalsDTO) {
        return this.animalsService.createAnimals(animalsDTO);
    }

    @PutMapping("/update-animalsDTO")
    public ResponseEntity updateAnimals(@RequestBody AnimalsDTO animalsDTO) {
        return this.animalsService.updateAnimals(animalsDTO);
    }

    @DeleteMapping("/remove-animals")
    public ResponseEntity deleteAnimals(@RequestParam String id) {
        return this.animalsService.deleteAnimalsById(id);
    }
}
