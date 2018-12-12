package it.unitn.mirkomarchiori1.webarchex11.service;

import it.unitn.mirkomarchiori1.webarchex11.model.Professor;
import it.unitn.mirkomarchiori1.webarchex11.model.Student;
import it.unitn.mirkomarchiori1.webarchex11.repository.ProfessorRepository;
import it.unitn.mirkomarchiori1.webarchex11.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetupService {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ProfessorRepository professorRepository;

    public String createStudent(Student student) {
        studentRepository.save(student);
        Student resStudent = studentRepository.findByMatriculationNumber(student.getMatriculationNumber()).get(0);
        String resString = "User " + resStudent.getName() + " " + resStudent.getSurname() + " " + resStudent.getMatriculationNumber() + " created";
        return resString;
    }

    public String createProfessor(Professor professor) {
        professorRepository.save(professor);
        Professor resProfessor = professorRepository.findByNameAndSurname(professor.getName(), professor.getSurname()).get(0);
        String resString = "Professor " + resProfessor.getName() + " " + resProfessor.getSurname() + " created";
        return resString;
    }

}
