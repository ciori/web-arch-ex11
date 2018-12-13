package it.unitn.mirkomarchiori1.webarchex11.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ApplicationController {

    @GetMapping("/")
    public String home() {
        return "home";
    }

    // TODO: Problem while saving Relations (and print of relations)
    // TODO: Implement Transactions

}
