package fr.serfa.biblioWeb.controller;

import fr.serfa.biblioWeb.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;

@RestController
public class MonPremierControlleur {
    @GetMapping("/")
    public String helloWorld(){return "Hello world";}

    @GetMapping("/color")
    public Color getColor(){return Color.magenta;}

    @GetMapping("/user")
    public User getUser(){
        User u = new User("Fred", 10);
        return u;
    }



}
