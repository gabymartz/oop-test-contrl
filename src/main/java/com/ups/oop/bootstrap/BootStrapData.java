package com.ups.oop.bootstrap;

import com.ups.oop.entity.*;
import com.ups.oop.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
 public class BootStrapData implements CommandLineRunner {
    private final PersonRepository personRepository;
    private final AnimalsRepository animalsRepository;
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final ClientRepository clientRepository;
    private final WorkerRepository workerRepository;
    private final EditorialRepository editorialRepository;


    public BootStrapData(PersonRepository personRepository, AnimalsRepository animalsRepository, AuthorRepository authorRepository, BookRepository bookRepository, ClientRepository clientRepository, EditorialRepository editorialRepository, WorkerRepository workerRepository) {
        this.personRepository = personRepository;
        this.animalsRepository = animalsRepository;
        this.authorRepository = authorRepository;
        this.clientRepository = clientRepository;
        this.workerRepository = workerRepository;
        this.bookRepository = bookRepository;
        this.editorialRepository = editorialRepository;
    }

    public void createPeople() {

        //PERSON
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
    }
    //ANIMALS

    public void createAnimals() {
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
    }

    public void createBooksAuthorsAndEditorials() {
        //BOOKS AND AUTHORS
        //#1 ALEJANDRO DUMAS
        Author au1 = new Author();
        au1.setName("Alejandro");
        au1.setLastname("Dumas");
        authorRepository.save(au1);

        // BOOKS

        Book b1 = new Book();
        b1.setTitle("Conde de Montecristo");
        b1.setEditorial("Pearson");
        b1.setAuthor(au1);
        bookRepository.save(b1);

        au1.getBooks().add(b1);
        authorRepository.save(au1);

        Book b5 = new Book();
        b5.setTitle("Los Tres Mosqueteros");
        b5.setEditorial("Pearson");
        b5.setAuthor(au1);
        bookRepository.save(b5);

        au1.getBooks().add(b5);
        authorRepository.save(au1);

        //#2 JK ROWLING
        Author au2 = new Author();
        au2.setName("J.K.");
        au2.setLastname("Rowling");
        authorRepository.save(au2);

        //BOOKS

        Book b2 = new Book();
        b2.setTitle("Harry Potter y la Piedra Filosofal");
        b2.setEditorial("Us Editorial");
        b2.setAuthor(au2);
        bookRepository.save(b2);

        au2.getBooks().add(b2);
        authorRepository.save(au2);

        Book b6 = new Book();
        b6.setTitle("Harry Potter y la Cámara Secreta");
        b6.setEditorial(" Us Editorial");
        b6.setAuthor(au2);
        bookRepository.save(b6);

        au2.getBooks().add(b6);
        authorRepository.save(au2);

        //#3 GABRIEL GARCIA MARQUEZ

        Author au3 = new Author();
        au3.setName("Gabriel");
        au3.setLastname("Garcia Marquez");
        authorRepository.save(au3);

        //BOOKS

        Book b3 = new Book();
        b3.setTitle("Cien años de Soledad");
        b3.setEditorial("Us Editorial");
        b3.setAuthor(au3);
        bookRepository.save(b3);

        au3.getBooks().add(b3);
        authorRepository.save(au3);

        Book b4 = new Book();
        b4.setTitle("Nos Vemos en Agosto");
        b4.setEditorial("Penguin Books");
        b4.setAuthor(au3);
        bookRepository.save(b4);

        au3.getBooks().add(b4);
        authorRepository.save(au3);

        //EDITORIALS
        Editorial e1 = new Editorial();
        e1.setName("Pearson");
        e1.getBooks().add(b1);
        e1.getBooks().add(b2);
        editorialRepository.save(e1);

        Editorial e2 = new Editorial();
        e2.setName("LNS");
        e2.getBooks().add(b2);
        e2.getBooks().add(b3);
        editorialRepository.save(e2);

        //books Join with Editorial
        b1.getEditorials().add(e1);
        bookRepository.save(b1);

        b2.getEditorials().add(e1);
        b2.getEditorials().add(e2);
        bookRepository.save(b2);

        b3.getEditorials().add(e2);
        bookRepository.save(b3);
    }

    public void createClients() {
        //CLIENTS
        Client c1 = new Client();
        c1.setClientCode("C-0001");
        c1.setName("Juan");
        c1.setLastname("Pia");
        c1.setAge(20);
        clientRepository.save(c1);

        Client c2 = new Client();
        c2.setClientCode("C-0002");
        c2.setName("María");
        c2.setLastname("Lopez");
        c2.setAge(35);
        clientRepository.save(c2);
    }

    public void createWorkers() {
        //WORKERS
        Worker w1 = new Worker();
        w1.setWorkerCode("W-0001");
        w1.setName("Isaac");
        w1.setLastname("Macías");
        w1.setAge(25);
        workerRepository.save(w1);

        Worker w2 = new Worker();
        w2.setWorkerCode("W-0002");
        w2.setName("Patricio");
        w2.setLastname("Andrade");
        w2.setAge(28);
        workerRepository.save(w2);

    }
    @Override
    public void run(String... args) throws Exception {
        createPeople();
        createAnimals();
        createBooksAuthorsAndEditorials();
        createClients();
        createWorkers();

        System.out.println("--------- Started BootstrapData -------------");
        System.out.println("Number of Persons: " + personRepository.count());
        System.out.println("Number of Animals: " + animalsRepository.count());
        System.out.println("Number of Authors: " + authorRepository.count());
        System.out.println("Number of Books: " + bookRepository.count());
        System.out.println("Number of Clients: " + clientRepository.count());
        System.out.println("Number of Workers: " + workerRepository.count());
    }
}
