package com.ups.oop.service;

import com.ups.oop.dto.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {
    private List<Person> personList = new ArrayList<>();

    public Person createPerson(Person person) {
        personList.add(person);
        return person;
    }

    public List<Person> getAllPeople(){
        return personList;
    }

    public Person getPersonById(String id){
        Person person = new Person();
        for(Person per : personList){
            if(id.equalsIgnoreCase(per.getId())){
                return per;
            }
        }
        return person;
    }

    public Person updatePerson(String id , Person person) {
        for (int i = 0; i < personList.size(); i++) {
            Person currentPer = personList.get(i);
            if (person.getId().equalsIgnoreCase(currentPer.getId())) {
                personList.set(i, person);
            }

        }
        return person;

    }



    public String deletePersonById(String id){
        String message = "Person with id " + id;
        for(Person per : personList){
            if(id.equalsIgnoreCase(per.getId())){
                personList.remove(per);
                return message + "removed successfully";
            }
        }
        return message + " not found";
    }
}
