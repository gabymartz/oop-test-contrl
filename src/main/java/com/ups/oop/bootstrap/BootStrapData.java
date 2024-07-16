package com.ups.oop.bootstrap;

import com.ups.oop.entity.Animals;
import com.ups.oop.entity.Person;
import com.ups.oop.repository.AnimalsRepository;
import com.ups.oop.repository.PersonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {
    private final PersonRepository personRepository;
    private final AnimalsRepository animalsRepository;

    public BootStrapData(PersonRepository personRepository, AnimalsRepository animalsRepository ){
        this.personRepository = personRepository;
        this.animalsRepository = animalsRepository;
    }
    @Override
    public void run(String... args)throws Exception {
        Person p1 = new Person();
        p1.setPersonId("0955889192");
        p1.setName("Juan");
        p1.setLastname("Benalcazar");
        p1.setAge(10);

        Person p2 = new Person();
        p2.setPersonId("0955889191");
        p2.setName("Gaby");
        p2.setLastname("Martinez");
        p2.setAge(20);

        personRepository.save(p1);
        personRepository.save(p2);


        Animals a1 = new Animals();
        a1.setAnimalsId("001");
        a1.setName("Osito");
        a1.setBreath("snau");
        a1.setColor("White");
        a1.setWeight(1.0);
        a1.setHeight(1.0);
        a1.setLength(1.30);

        animalsRepository.save(a1);


        System.out.println("------------STARTED BOOTSTRAPDATA--------");
        System.out.println("Number of Persons:" + personRepository.count());
        System.out.println("Number of Animals:" + animalsRepository.count());

    }
}
