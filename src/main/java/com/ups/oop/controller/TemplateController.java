package com.ups.oop.controller;

import com.ups.oop.dto.BookDTO;
import com.ups.oop.service.AnimalsService;
import com.ups.oop.service.BookService;
import com.ups.oop.service.PersonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class TemplateController {
    private final PersonService personService;
    private final AnimalsService animalsService;
    private final BookService bookService;


    public TemplateController(PersonService personService, AnimalsService animalsService, BookService bookService) {
        this.personService = personService;
        this.animalsService = animalsService;
        this.bookService = bookService;
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
    public String getBook(Model model){
        List<BookDTO> books = bookService.getBook();
        model.addAttribute("books", bookService.getBook());
        return "book/list";
    }
}
