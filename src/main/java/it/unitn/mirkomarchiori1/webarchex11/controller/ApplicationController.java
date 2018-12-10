package it.unitn.mirkomarchiori1.webarchex11.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ApplicationController {

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/setup")
    public String setup() {
        return "setup";
    }

    @GetMapping("/operations")
    public String operations() {
        return "operations";
    }

    @GetMapping("/show")
    public String show() {
        return "show";
    }

}
