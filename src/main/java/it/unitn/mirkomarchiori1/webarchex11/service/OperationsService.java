package it.unitn.mirkomarchiori1.webarchex11.service;

import it.unitn.mirkomarchiori1.webarchex11.model.Course;
import it.unitn.mirkomarchiori1.webarchex11.model.Student;
import it.unitn.mirkomarchiori1.webarchex11.repository.CourseRepository;
import it.unitn.mirkomarchiori1.webarchex11.repository.ExamRepository;
import it.unitn.mirkomarchiori1.webarchex11.repository.ProfessorRepository;
import it.unitn.mirkomarchiori1.webarchex11.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class OperationsService {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ProfessorRepository professorRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private ExamRepository examRepository;

    public String enrollStudentToCourse(Integer studentMatriculationNumber, String courseName) {
        if (studentRepository.findByMatriculationNumber(studentMatriculationNumber).size() == 0) {
            return "ERROR: User " + studentMatriculationNumber + " does not exist!";
        }
        if (courseRepository.findByName(courseName).size() == 0) {
            return "ERROR: Course " + courseName + " does not exist!";
        }
        Student student = studentRepository.findByMatriculationNumber(studentMatriculationNumber).get(0);
        Course course = courseRepository.findByName(courseName).get(0);
        Set<Course> courses = student.getCourses();
        courses.add(course);
        student.setCourses(courses);
        studentRepository.save(student);
        return "User " + student.getName() + " " + student.getSurname() + " " + student.getMatriculationNumber() + " enrolled in Course " + course.getName();
    }

}
