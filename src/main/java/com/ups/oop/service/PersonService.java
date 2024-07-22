package com.ups.oop.service;

import com.ups.oop.dto.PersonDTO;
import com.ups.oop.entity.Person;
import com.ups.oop.repository.PersonRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    private final PersonRepository personRepository; //Inyección por dependencia

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository; //Inyección por dependencia por constructor
    }

    private List<PersonDTO> personDTOList = new ArrayList<>();

    public ResponseEntity createPerson(PersonDTO personDTO) {
        String personId = personDTO.getId();
        Optional<Person> personOptional = personRepository.findByPersonId(personId);

        if (personOptional.isPresent()) {
            String errorMessage = "Person with id " + personId + " doesn't exist :C";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);

        } else {
            if (personDTO.getName().contains(" ")) {
                Person personRecord = new Person();
                personRecord.setPersonId(personDTO.getId());
                String[] nameStrings = personDTO.getName().split(" ");
                String name = nameStrings[0];
                String lastname = nameStrings[1];
                personRecord.setName(name);
                personRecord.setLastname(lastname);
                personRecord.setAge(personDTO.getAge());
                personRepository.save(personRecord);
                return ResponseEntity.status(HttpStatus.OK).body(personDTO);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Person name must contain two strings separated by a whitespaces");
            }

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
        Iterable <Person> personIterable = personRepository.findAll(); //Iterable colección de datos similar a lista
        List<PersonDTO> peopleList = new ArrayList<>();

        for(Person per: personIterable){
            PersonDTO person= new PersonDTO();
            person.setId(per.getPersonId());
            person.setName(per.getName() + "-" + per.getLastname());
            person.setAge(per.getAge());
            peopleList.add(person);
        }

        if(peopleList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("PersonDTO List Not Found :C");
        }
        return ResponseEntity.status(HttpStatus.OK).body(peopleList);
    }

    public ResponseEntity getPersonById(String personId){

       //Optional<Person> personOptional = personRepository.findById(Long.valueOf(id));
       Optional<Person> personOptional = personRepository.findByPersonId(personId);
       if(personOptional.isPresent()){
           //if record was found
           Person personFound = personOptional.get();
           PersonDTO person = new PersonDTO((personFound.getPersonId()),
                   personFound.getName() + " - " + personFound.getLastname(),
                   personFound.getAge());

           return ResponseEntity.status(HttpStatus.OK).body(person);
       } else {
           //if record wasn't found
           String errorMessage = "Person with id " + personId + " doesn't exist :C";
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
       }
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
