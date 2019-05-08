package com.ukrsibbank.test.task.solution.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({ "/", "/home", "/index" })
public class HomeController {

    @GetMapping
    public String home() {
        return "<h1>Hello, Ukrsibbank!</h1>";
    }
}
