package com.ups.oop.service;

import com.ups.oop.dto.PersonDTO;
import com.ups.oop.entity.Person;
import com.ups.oop.repository.PersonRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {
    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    private List<PersonDTO> personDTOList = new ArrayList<>();

    public ResponseEntity createPerson(PersonDTO personDTO) {
        String personId = personDTO.getId();
        boolean wasFound = findPerson(personId);
        if (wasFound){
            String errorMessage = "PersonDTO with id " + personId + " already exists ;)";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(errorMessage);
        } else {
            personDTOList.add(personDTO);
            return ResponseEntity.status(HttpStatus.OK).body(personDTO);
        }
    }

    private boolean findPerson(String id) {
        for (PersonDTO personDTO : personDTOList) {
            if (id.equals(personDTO.getId())) {
                return true;
            }
        }
        return false;
    }

    public ResponseEntity getAllPeople(){
        Iterable <Person> personIterable = personRepository.findAll();
        List<PersonDTO> peopleList = new ArrayList<>();

        for(Person p: personIterable){
            PersonDTO person = new PersonDTO(p.getPersonId(), p.getName(), p.getLastname(), p.getAge());
            peopleList.add(person);
        }

        if(peopleList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("PersonDTO List Not Found :C");
        }
        return ResponseEntity.status(HttpStatus.OK).body(peopleList);
    }

    public ResponseEntity getPersonById(String id){
        for(PersonDTO per : personDTOList){
            if(id.equalsIgnoreCase(per.getId())){
                return ResponseEntity.status(HttpStatus.OK).body(per);
            }
        }
        String errorMessage = "PersonDTO with id " + id + " doesn't exist :C";
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }

    private int findIndexById(String id){
        int index = 0;
        for (PersonDTO p: personDTOList){
            if(id.equals(p.getId())){
                return index;
            }
            index++;
        }
        return -1;
    }

    public ResponseEntity updatePerson(PersonDTO personDTO) {
        int updateIndex = findIndexById(personDTO.getId());
        if(updateIndex != -1){
            personDTOList.set(updateIndex, personDTO);
            return ResponseEntity.status(HttpStatus.OK).body(personDTO);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("PersonDTO with id " + personDTO.getId() + " doesn't exist :C");

    }

    public ResponseEntity deletePersonById(String id){
        String message = "PersonDTO with id " + id;
        for(PersonDTO per : personDTOList){
            if(id.equalsIgnoreCase(per.getId())){
                personDTOList.remove(per);
                return ResponseEntity.status(HttpStatus.OK).body(message + " removed succesfully");
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message + " Not found");
    }
}
