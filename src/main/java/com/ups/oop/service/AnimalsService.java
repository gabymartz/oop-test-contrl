package com.ups.oop.service;

import com.ups.oop.dto.AnimalsDTO;
import com.ups.oop.entity.Animals;
import com.ups.oop.repository.AnimalsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AnimalsService {
    private final AnimalsRepository animalsRepository;
    private List<AnimalsDTO> animalsDTOList = new ArrayList<>();

    public AnimalsService(AnimalsRepository animalsRepository) {
        this.animalsRepository = animalsRepository;
    }


    public ResponseEntity createAnimals(AnimalsDTO animalsDTO) {
        boolean wasFound = findAnimals(animalsDTO.getId());
        if (wasFound){
            String errorMessage = "Animals with id " + animalsDTO.getId() + " already exists ;)";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        } else {
            animalsDTOList.add(animalsDTO);
            return ResponseEntity.status(HttpStatus.OK).body(animalsDTO);
        }
    }

    private boolean findAnimals(String id) {
        for (AnimalsDTO animalsDTO : animalsDTOList) {
            if (id.equalsIgnoreCase(animalsDTO.getId())) {
                return true;
            }
        }
        return false;
    }
    public ResponseEntity getAllAnimals(){

        List<AnimalsDTO> animalsList = getAnimals();

        if(animalsList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Animal List Not Found :C");
        }
        return ResponseEntity.status(HttpStatus.OK).body(animalsList);
    }
    //TemplateController
    public List<AnimalsDTO> getAnimals() {
        Iterable<Animals> animalsIterable = animalsRepository.findAll();
        List<AnimalsDTO> animalsList = new ArrayList<>();
        for(Animals anim : animalsIterable){
            AnimalsDTO animal = new AnimalsDTO();
            animal.setAnimalsCode(anim.getName() + "-" + anim.getBreed() + "-" + anim.getColor());
            animal.setPetName(anim.getPetName());
            animal.setWeight(anim.getWeight());
            animal.setHeight(anim.getHeight());
            animal.setLength(anim.getLength());
            animalsList.add(animal);
        }
        return animalsList;
    }

    public ResponseEntity getAnimalsById(String id) {
        Optional<Animals> animalsOptional = animalsRepository.findById(Long.valueOf(id));

        if (animalsOptional.isPresent()) {
            Animals animalsFound = animalsOptional.get();
            AnimalsDTO animal = new AnimalsDTO(
                    animalsFound.getName() + "-" + animalsFound.getBreed() + "-" + animalsFound.getColor(),
                    animalsFound.getPetName(),
                    animalsFound.getWeight(),
                    animalsFound.getHeight(),
                    animalsFound.getLength());
            return ResponseEntity.status(HttpStatus.OK).body(animal);
        } else {
            String errorMessage = "Animals with id " + id + " doesn't exist :C";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }

    private int findIndexById(String id){
        int index = 0;
        for (AnimalsDTO a: animalsDTOList){
            if(id.equalsIgnoreCase(a.getId())){
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
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Animal with id " + animalsDTO.getId() + " doesn't exist :C");

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


