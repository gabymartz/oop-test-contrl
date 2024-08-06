package com.ups.oop.controller;

import com.ups.oop.service.AnimalsService;
import com.ups.oop.service.PersonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TemplateController {
    private final PersonService personService;
    private final AnimalsService animalsService;

    public TemplateController(PersonService personService, AnimalsService animalsService) {
        this.personService = personService;
        this.animalsService = animalsService;
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
}
