package fr.serfa.biblioWeb.controller;

import fr.serfa.biblioWeb.model.User;
import org.springframework.web.bind.annotation.*;

import java.awt.*;

@RestController
public class MonPremierControlleur {
    @GetMapping("/")
    public String helloWorld(){return "Hello world";
    }

    @GetMapping("/hello")
    public String helloSomeone(@RequestParam String nom){
        return "Hello" + nom;
    }

    @GetMapping("/hello/{nom}")
    public String helloSomeoneBis(@PathVariable String nom){
        return "Hello" + nom;
    }

    @GetMapping("/color")
    public Color getColor(){return Color.magenta;}

    @GetMapping("/user")
    public User getUser(){
        User u = new User("Fred", 10);
        return u;
    }

    @PostMapping("/user")
    public User postUser(@RequestBody User u){
        return u;
    }



}
