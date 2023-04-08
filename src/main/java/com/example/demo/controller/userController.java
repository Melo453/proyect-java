package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class userController {
    @GetMapping()
    private String home(){
        return "<h1> Bienvenidos </h1>";
    }
    @GetMapping("/user")
    private String user(){
        return "<h1> Bienvenido usuario </h1>";
    }
    @GetMapping("/admin")
    private String admin(){
        return "<h1> Bienvenido admin </h1>";
    }
}
