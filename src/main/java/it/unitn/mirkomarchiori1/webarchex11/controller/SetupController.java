package it.unitn.mirkomarchiori1.webarchex11.controller;

import it.unitn.mirkomarchiori1.webarchex11.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SetupController {

    @GetMapping("/setup")
    public String setupForm(Model model) {
        model.addAttribute("student", new Student());
        return "setup";
    }

    @PostMapping("/createStudent")
    public String createStudentSubmit(@ModelAttribute Student student) {
        return "result";
    }

}
