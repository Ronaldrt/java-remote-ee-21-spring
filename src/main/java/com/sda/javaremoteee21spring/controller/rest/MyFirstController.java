package com.sda.javaremoteee21spring.controller.rest;

import com.sda.javaremoteee21spring.dto.Person;
import com.sda.javaremoteee21spring.service.MyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api")
// @Slf4j
public class MyFirstController {

    private final MyService service;

    //This is what happens under the hood with Lombok @Slf4j
    private static final Logger log = LoggerFactory.getLogger(MyFirstController.class);

    public MyFirstController(MyService service) {
        log.info("MyFirstController constructor");
        this.service = service;
    }

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

    // ?param1=value1&param2=value2  - this is a generic way to pass parameters to your backend
    // /api/try-login?login=ronald&password=12345
    @GetMapping("/try-login")
    public String logIn(@RequestParam(defaultValue = "JohnDoe", value = "login") String userLogin, @RequestParam(defaultValue = "pass", value = "password") String userPassword){
        log.info("received login: [{}] and password [{}]", userLogin, userPassword);
        return "OK";
    }

    @PostMapping("/try-login")
    public ResponseEntity<String>logInWithPost(@RequestParam("login") String userLogin, @RequestParam("password") String userPassword){
        log.info("login with post");
        log.info("received login: [{}] and password [{}]", userLogin, userPassword);
        if (userLogin == null || userPassword == null){
            return ResponseEntity.badRequest().body("Provide credentials");
        }
        return ResponseEntity.ok("Success");
    }

    @GetMapping("/secret")
    public String getOneSecret(){
       String secretToShare = service.shareOneSecret();
       log.info("Sharing [{}] secret with user", secretToShare);
       return secretToShare;
    }
}
