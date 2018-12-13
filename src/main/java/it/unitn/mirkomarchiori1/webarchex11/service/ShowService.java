package it.unitn.mirkomarchiori1.webarchex11.service;

import it.unitn.mirkomarchiori1.webarchex11.model.Course;
import it.unitn.mirkomarchiori1.webarchex11.model.Exam;
import it.unitn.mirkomarchiori1.webarchex11.model.Professor;
import it.unitn.mirkomarchiori1.webarchex11.model.Student;
import it.unitn.mirkomarchiori1.webarchex11.repository.CourseRepository;
import it.unitn.mirkomarchiori1.webarchex11.repository.ExamRepository;
import it.unitn.mirkomarchiori1.webarchex11.repository.ProfessorRepository;
import it.unitn.mirkomarchiori1.webarchex11.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowService {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ProfessorRepository professorRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private ExamRepository examRepository;

    public String showData() {
        String resString = "<div>";
        resString = resString + "<h3>ALL STORED DATA</h3>";

        resString = resString + "<h5>STUDENTS:</h5>";
        for (Student student: studentRepository.findAll()) {
            resString = resString + "<p>" + student.getName() + " " + student.getSurname() + " " + student.getMatriculationNumber() + "</p>";
        }

        resString = resString + "<h5>PROFESSORS:</h5>";
        for (Professor professor: professorRepository.findAll()) {
            resString = resString + "<p>" + professor.getName() + " " + professor.getSurname() + "</p>";
        }

        resString = resString + "<h5>COURSES:</h5>";
        for (Course course: courseRepository.findAll()) {
            resString = resString + "<p>" + course.getName() + "</p>";
            if (course.getProfessor() != null) {
                resString = resString + "<p style='text-indent: 2em;'>teacher:" + course.getProfessor().getName() + "</p>";
            }
            else {
                resString = resString + "<p style='text-indent: 2em;'>teacher:</p>";
            }
            resString = resString + "<p style='text-indent: 2em;'>students:</p>";
            for (Student student: course.getStudents()) {
                resString = resString + "<p style='text-indent: 4em;'>" + student.getName() +
                        " " + student.getSurname() + " " + student.getMatriculationNumber() + "</p>";
            }
        }

        resString = resString + "<h5>EXAMS:</h5>";
        for (Exam exam: examRepository.findAll()) {
            resString = resString + "<p><b>" + exam.getName() + "</b> on " + exam.getDate().toString() + " with enrolled students - grades:</p>";
            List<Student> students = exam.getStudents();
            List<Integer> grades = exam.getGrades();
            if (students.size() == 0) {
                resString = resString + "<p style='text-indent: 2em;'>No Students registered to this Exam!</p>";
            }
            for (int i=0;i<students.size();i++) {
                resString = resString + "<p style='text-indent: 2em;'>" + students.get(i).getName() +
                        " " + students.get(i).getSurname() + " " + students.get(i).getMatriculationNumber() + " - " + grades.get(i) + "</p>";
            }
        }

        resString = resString + "</div>";
        return resString;
    }

    public String showStudentsInCourse(String name) {
        if (courseRepository.findByName(name).size() == 0) {
            return "<div><p>No available Course named <b>" + name + "</b> found!<p></div>";
        }
        String resString = "<div>";
        resString = resString + "<h3>LIST OF ENROLLED STUDENTS IN THE COURSE: <b>" + name + "</b></h3>";
        if (courseRepository.findByName(name).get(0).getStudents().size() == 0) {
            resString = resString + "<p style='text-indent: 2em;'>No Students enrolled in this Course!</p>";
        }
        else {
            for (Student student: courseRepository.findByName(name).get(0).getStudents()) {
                resString = resString + "<p style='text-indent: 2em;'>" + student.getName() +
                        " " + student.getSurname() + " " + student.getMatriculationNumber() + "</p>";
            }
        }
        resString = resString + "</div>";
        return resString;
    }

}
