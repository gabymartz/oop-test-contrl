package com.ups.oop.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test")
    public String test(){
        return "Hello world, this is my first Project!....";
    }

    @GetMapping("/hello")
    public String hello(@RequestParam String name,
                        @RequestParam String lastname,
                        @RequestParam(required = false) Integer age){
        String message = "This is my first SpringBootProject!" +
                " My name is "+ name + " " + lastname;
        if(age != null){
            message += " and I am " + age  + " years old";
        }
        return message;
    }

    @GetMapping("/concat/{name}/{lastname}/{age}")
    public String concatenate(@PathVariable String name,
                              @PathVariable String lastname,
                              @PathVariable(required = false) Integer age){
        String message = "This is my second rest service!, and my name is: "
                + name + " " + lastname;
        if (age != null) {
            message += " and my age is " + age;
        }
        return message;
    }
}
