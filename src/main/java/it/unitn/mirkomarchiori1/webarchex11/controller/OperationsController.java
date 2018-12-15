package it.unitn.mirkomarchiori1.webarchex11.controller;

import it.unitn.mirkomarchiori1.webarchex11.service.OperationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OperationsController {

    @Autowired
    private OperationsService operationsService;

    @GetMapping("/operations")
    public String operationsPage() {
        return "operations";
    }

    @PostMapping("/enrollStudentToCourse")
    public String enrollStudentToCourse(@RequestParam("studentMatriculationNumber") Integer studentMatriculationNumber,
                                        @RequestParam("courseName") String courseName,
                                        Model model) {
        String result = operationsService.enrollStudentToCourse(studentMatriculationNumber, courseName);
        model.addAttribute("result", result);
        return "operationsResult";
    }

    @PostMapping("/registerStudentToExam")
    public String registerStudentToExam(@RequestParam("studentMatriculationNumber") Integer studentMatriculationNumber,
                                        @RequestParam("examName") String examName,
                                        Model model) {
        String result = operationsService.registerStudentToExam(studentMatriculationNumber, examName);
        model.addAttribute("result", result);
        return "operationsResult";
    }

    /*@PostMapping("/assignProfessorToCourse")
    public String assignProfessorToCourse(@RequestParam("professorName") String professorName,
                                          @RequestParam("professorSurname") String professorSurname,
                                          @RequestParam("courseName") String courseName,
                                          Model model) {
        String result = operationsService.assignProfessorToCourse(professorName, professorSurname, courseName);
        model.addAttribute("result", result);
        return "operationsResult";
    }

    @PostMapping("/gradeStudent")
    public String gradeStudent(@RequestParam("studentMatriculationNumber") Integer studentMatriculationNumber,
                               @RequestParam("examName") String examName,
                               @RequestParam("grade") Integer grade,
                               Model model) {
        String result = operationsService.gradeStudent(studentMatriculationNumber, examName, grade);
        model.addAttribute("result", result);
        return "operationsResult";
    }*/

}
