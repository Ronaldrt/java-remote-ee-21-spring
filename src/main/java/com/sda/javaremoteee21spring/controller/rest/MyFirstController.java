package com.sda.javaremoteee21spring.controller.rest;

import com.sda.javaremoteee21spring.dto.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MyFirstController {

    @GetMapping("/name")
    public String showMeMyName(){
        return "Ronald";
    }

    @GetMapping("/me")
    public Person justMe(){
        return new Person("Ronald", "Rätsep", 32);
    }

    @GetMapping("me-and-friend")
    public List<Person> meAndFriend (){
        return List.of(
                new Person("Ronald", "Rätsep", 32),
                new Person("John", "Doe", 35)
        );

    }
}
