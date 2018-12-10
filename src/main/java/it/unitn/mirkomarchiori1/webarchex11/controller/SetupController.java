package it.unitn.mirkomarchiori1.webarchex11.controller;

import it.unitn.mirkomarchiori1.webarchex11.controller.dto.CreateStudentForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SetupController {

    @RequestMapping(value = "/createStudent", method = RequestMethod.POST)
    public String createStudent(Model model, CreateStudentForm createStudentForm) {

        // ...

        return "setup";
    }

}
