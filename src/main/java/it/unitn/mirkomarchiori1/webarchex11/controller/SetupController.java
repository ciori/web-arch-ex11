package it.unitn.mirkomarchiori1.webarchex11.controller;

import it.unitn.mirkomarchiori1.webarchex11.model.Course;
import it.unitn.mirkomarchiori1.webarchex11.model.Exam;
import it.unitn.mirkomarchiori1.webarchex11.model.Professor;
import it.unitn.mirkomarchiori1.webarchex11.model.Student;
import it.unitn.mirkomarchiori1.webarchex11.service.SetupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SetupController {

    @Autowired
    private SetupService setupService;

    @GetMapping("/setup")
    public String setupForm(Model model) {
        model.addAttribute("student", new Student());
        model.addAttribute("professor", new Professor());
        model.addAttribute("course", new Course());
        model.addAttribute("exam", new Exam());
        return "setup";
    }

    @PostMapping("/createStudent")
    public String createStudentSubmit(@ModelAttribute Student student, Model model) {
        String result = setupService.createStudent(student);
        model.addAttribute("result", result);
        return "setupResult";
    }

    @PostMapping("/createProfessor")
    public String createProfessorSubmit(@ModelAttribute Professor professor, Model model) {
        String result = setupService.createProfessor(professor);
        model.addAttribute("result", result);
        return "setupResult";
    }

    @PostMapping("/createCourse")
    public String createCourseSubmit(@ModelAttribute Course course, Model model) {
        String result = setupService.createCourse(course);
        model.addAttribute("result", result);
        return "setupResult";
    }

    @PostMapping("/createExam")
    public String createExamSubmit(@ModelAttribute Exam exam, Model model) {
        String result = setupService.createExam(exam);
        model.addAttribute("result", result);
        return "setupResult";
    }

}
