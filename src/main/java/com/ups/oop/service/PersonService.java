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
}