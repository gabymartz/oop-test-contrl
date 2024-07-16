package com.ups.oop.service;

import com.ups.oop.dto.AnimalsDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnimalsService {
    private List<AnimalsDTO> animalsDTOList = new ArrayList<>();

    public ResponseEntity createAnimals(AnimalsDTO animalsDTO) {
        String animalsId = animalsDTO.getId();
        boolean wasFound = findAnimals(animalsId);
        if (wasFound){
            String errorMessage = "Animals with id " + animalsId + " already exists ;)";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(errorMessage);
        } else {
            animalsDTOList.add(animalsDTO);
            return ResponseEntity.status(HttpStatus.OK).body(animalsDTO);
        }
    }

    private boolean findAnimals(String id) {
        for (AnimalsDTO animalsDTO : animalsDTOList) {
            if (id.equals(animalsDTO.getId())) {
                return true;
            }
        }
        return false;
    }
    public ResponseEntity getAllAnimals(){
        if(animalsDTOList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("AnimalsDTO List Not Found :C");
        }
        return ResponseEntity.status(HttpStatus.OK).body(animalsDTOList);
    }
    public ResponseEntity getAnimalsById(String id){
        for(AnimalsDTO anils : animalsDTOList){
            if(id.equalsIgnoreCase(anils.getId())){
                return ResponseEntity.status(HttpStatus.OK).body(anils);
            }
        }
        String errorMessage = "Animals with id " + id + " doesn't exist :C";
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }
    private int findIndexById(String id){
        int index = 0;
        for (AnimalsDTO anils: animalsDTOList){
            if(id.equals(anils.getId())){
                return index;
            }
            index++;
        }
        return -1;
    }
    public ResponseEntity updateAnimals(AnimalsDTO animalsDTO) {
        int updateIndex = findIndexById(animalsDTO.getId());
        if(updateIndex != -1){
            animalsDTOList.set(updateIndex, animalsDTO);
            return ResponseEntity.status(HttpStatus.OK).body(animalsDTO);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Animals with id " + animalsDTO.getId() + " doesn't exist :C");

    }
    public ResponseEntity deleteAnimalsById(String id){
        String message = "Animals with id " + id;
        for(AnimalsDTO anils : animalsDTOList){
            if(id.equalsIgnoreCase(anils.getId())){
                animalsDTOList.remove(anils);
                return ResponseEntity.status(HttpStatus.OK).body(message + " removed succesfully");
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message + " Not found");
    }
}


