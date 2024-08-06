package com.ups.oop.bootstrap;

import com.ups.oop.entity.Animals;
import com.ups.oop.entity.Person;
import com.ups.oop.entity.Student;
import com.ups.oop.repository.AnimalsRepository;
import com.ups.oop.repository.PersonRepository;
import com.ups.oop.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {
    private final PersonRepository personRepository;
    private final AnimalsRepository animalsRepository;
    //private final StudentRepository studentRepository;

    public BootStrapData(PersonRepository personRepository, AnimalsRepository animalsRepository, StudentRepository studentRepository){
        this.personRepository = personRepository;
        this.animalsRepository = animalsRepository;
        //this.studentRepository = studentRepository;
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

        Person p3 = new Person();
        p3.setPersonId("0955889193");
        p3.setName("Daniel");
        p3.setLastname("Viloria");
        p3.setAge(21);


        personRepository.save(p1);
        personRepository.save(p2);
        personRepository.save(p3);


        Animals a1 = new Animals();
        a1.setPetName("Diego");
        a1.setName("Dog");
        a1.setBreed("schnauzer");
        a1.setColor("White");
        a1.setWeight(1.6);
        a1.setHeight(18);
        a1.setLength(14);

        Animals a2 = new Animals();
        a2.setPetName("Mía");
        a2.setName("Dog");
        a2.setBreed("Labrador");
        a2.setColor("Black");
        a2.setWeight(1.0);
        a2.setHeight(19);
        a2.setLength(13);

        Animals a3 = new Animals();
        a3.setPetName("Tita");
        a3.setName("Cat");
        a3.setBreed("Persian");
        a3.setColor("Black");
        a3.setWeight(1.3);
        a3.setHeight(30);
        a3.setLength(13);

        animalsRepository.save(a1);
        animalsRepository.save(a2);
        animalsRepository.save(a3);

        //Student s1 = new Student();
        //s1.setStudentCode("ups1");
        //s1.setName("Gabriela");
        //s1.setLastname("Martinez");

        //Student s2 = new Student();
        //s2.setStudentCode("ups2");
        //s2.setName("Gabriel");
        //s2.setLastname("Moran");

        //studentRepository.save(s1);
        //studentRepository.save(s2);

        System.out.println("------------STARTED BOOTSTRAPDATA--------");
        System.out.println("Number of Persons:" + personRepository.count());
        System.out.println("Number of Animals:" + animalsRepository.count());
        //System.out.println("Number of Students:" + studentRepository.count());

    }
}
