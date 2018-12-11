package it.unitn.mirkomarchiori1.webarchex11.controller;

import it.unitn.mirkomarchiori1.webarchex11.controller.dto.CreateStudentForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SetupController {

    @GetMapping("/createStudent")
    public String createStudentForm(Model model) {
        model.addAttribute("createStudentForm", new CreateStudentForm());
        return "createStudent";
    }

    @PostMapping("/createStudent")
    public String createStudentSubmit(@ModelAttribute CreateStudentForm createStudentForm) {
        return "setup";
    }

}
