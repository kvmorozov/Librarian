package ru.kmorozov.librarian.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by sbt-morozov-kv on 22.06.2016.
 */

@RestController
public class MainRestController {

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }
}
