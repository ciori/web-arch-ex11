package it.unitn.mirkomarchiori1.webarchex11.service;

import it.unitn.mirkomarchiori1.webarchex11.model.Professor;
import it.unitn.mirkomarchiori1.webarchex11.model.Student;
import it.unitn.mirkomarchiori1.webarchex11.repository.ProfessorRepository;
import it.unitn.mirkomarchiori1.webarchex11.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShowService {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ProfessorRepository professorRepository;

    public String showData() {
        String resString = "<div>";
        resString = resString + "<h3>ALL STORED DATA</h3>";

        resString = resString + "<h5>STUDENTS:</h5>";
        for(Student student: studentRepository.findAll()) {
            resString = resString + "<p>" + student.getName() + " " + student.getSurname() + " " + student.getMatriculationNumber() + "</p>";
        }

        resString = resString + "<h5>PROFESSORS:</h5>";
        for(Professor professor: professorRepository.findAll()) {
            resString = resString + "<p>" + professor.getName() + " " + professor.getSurname() + "</p>";
        }

        resString = resString + "</div>";
        return resString;
    }

}
