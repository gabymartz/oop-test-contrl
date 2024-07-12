package com.ups.oop.service;

import com.ups.oop.dto.Animals;
import com.ups.oop.dto.Person;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnimalsService {
    private List<Animals> animalsList = new ArrayList<>();

    public ResponseEntity createAnimals(Animals animals) {
        String animalsId = animals.getId();
        boolean wasFound = findAnimals(animalsId);
        if (wasFound){
            String errorMessage = "Animal with id " + animalsId + " already exists ;)";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(errorMessage);
        } else {
            animalsList.add(animals);
            return ResponseEntity.status(HttpStatus.OK).body(animals);
        }
    }

    private boolean findAnimals(String id) {
        for (Animals animals : animalsList) {
            if (id.equals(animals.getId())) {
                return true;
            }
        }
        return false;
    }
    public ResponseEntity getAllAnimals(){
        if(animalsList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Animals List Not Found :C");
        }
        return ResponseEntity.status(HttpStatus.OK).body(animalsList);
    }
    public ResponseEntity getAnimalsById(String id){
        for(Animals anils : animalsList){
            if(id.equalsIgnoreCase(anils.getId())){
                return ResponseEntity.status(HttpStatus.OK).body(anils);
            }
        }
        String errorMessage = "Animal with id " + id + " doesn't exist :C";
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }
    private int findIndexById(String id){
        int index = 0;
        for (Animals anils: animalsList){
            if(id.equals(anils.getId())){
                return index;
            }
            index++;
        }
        return -1;
    }
    public ResponseEntity updateAnimals(Animals animals) {
        int updateIndex = findIndexById(animals.getId());
        if(updateIndex != -1){
            animalsList.set(updateIndex, animals);
            return ResponseEntity.status(HttpStatus.OK).body(animals);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Animal with id " + animals.getId() + " doesn't exist :C");

    }
    public ResponseEntity deleteAnimalsById(String id){
        String message = "Animal with id " + id;
        for(Animals anils : animalsList){
            if(id.equalsIgnoreCase(anils.getId())){
                animalsList.remove(anils);
                return ResponseEntity.status(HttpStatus.OK).body(message + " removed succesfully");
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message + " Not found");
    }
}


