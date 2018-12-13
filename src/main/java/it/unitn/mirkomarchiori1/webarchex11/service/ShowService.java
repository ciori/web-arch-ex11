package it.unitn.mirkomarchiori1.webarchex11.service;

import it.unitn.mirkomarchiori1.webarchex11.model.Course;
import it.unitn.mirkomarchiori1.webarchex11.model.Professor;
import it.unitn.mirkomarchiori1.webarchex11.model.Student;
import it.unitn.mirkomarchiori1.webarchex11.repository.CourseRepository;
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
    @Autowired
    private CourseRepository courseRepository;

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

        resString = resString + "<h5>COURSES:</h5>";
        for(Course course: courseRepository.findAll()) {
            resString = resString + "<p>" + course.getName() + "</p>";
            if (course.getProfessor() != null) {
                resString = resString + "<p style='text-indent: 2em;'>teacher:" + course.getProfessor().getName() + "</p>";
            }
            else {
                resString = resString + "<p style='text-indent: 2em;'>teacher:</p>";
            }
            resString = resString + "<p style='text-indent: 2em;'>students:</p>";
            for (Student student: course.getStudents()) {
                resString = resString + "<p style='text-indent: 4em;'>teacher: " + course.getProfessor().getName() +
                        " " + student.getSurname() + " " + student.getMatriculationNumber() + "</p>";
            }
            resString = resString + "<p style='text-indent: 2em;'>exam on: ";
            if (course.getExam() != null) {
                resString = resString + "" + course.getExam().getDate().toString();
            }
            resString = resString + "</p>";
        }

        resString = resString + "</div>";
        return resString;
    }

}
