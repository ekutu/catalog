package com.nexus.catalog.web;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class HomeController {

    @GetMapping("/")
    public String getGreeting() {
        return "Welcome to the book catalog!";
    }
}
