package com.ups.oop.controller;

import com.ups.oop.dto.BookDTO;
import com.ups.oop.dto.ClientDTO;
import com.ups.oop.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class TemplateController {
    private final PersonService personService;
    private final AnimalsService animalsService;
    private final BookService bookService;
    private final ClientService clientService;
    private final WorkerService workerService;


    public TemplateController(PersonService personService, AnimalsService animalsService, BookService bookService, ClientService clientService, WorkerService workerService) {
        this.personService = personService;
        this.animalsService = animalsService;
        this.bookService = bookService;
        this.clientService = clientService;
        this.workerService = workerService;
    }

    @GetMapping("/template")
    public String getTemplate(Model model){
        return "template";
    }
    @GetMapping("/people")
    public String getPeople(Model model){
        model.addAttribute("people",  personService.getPeople());
        return "person/list";
    }
    @GetMapping("/animals")
    public String getAnimals(Model model){
        model.addAttribute("animals",  animalsService.getAnimals());
        return "animals/list";
    }
    @GetMapping("/books")
    public String getBooks(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        return "book/list";
    }
    @GetMapping("/clients")
    public String getClients(Model model) {
        model.addAttribute("clients", clientService.getClients());
        return "client/list";
    }
    @GetMapping("/workers")
    public String getWorkers(Model model) {
        model.addAttribute("workers", workerService.getWorkers());
        return "worker/list";
    }
}
