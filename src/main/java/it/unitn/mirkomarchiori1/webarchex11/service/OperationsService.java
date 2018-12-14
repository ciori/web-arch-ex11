package it.unitn.mirkomarchiori1.webarchex11.service;

import it.unitn.mirkomarchiori1.webarchex11.model.Course;
import it.unitn.mirkomarchiori1.webarchex11.model.Student;
import it.unitn.mirkomarchiori1.webarchex11.repository.CourseRepository;
import it.unitn.mirkomarchiori1.webarchex11.repository.ExamRepository;
import it.unitn.mirkomarchiori1.webarchex11.repository.ProfessorRepository;
import it.unitn.mirkomarchiori1.webarchex11.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public String enrollStudentToCourse(Integer studentMatriculationNumber, String courseName) {
        if (studentRepository.findByMatriculationNumber(studentMatriculationNumber).size() == 0) {
            return "ERROR: User " + studentMatriculationNumber + " does not exist!";
        }
        if (courseRepository.findByName(courseName).size() == 0) {
            return "ERROR: Course " + courseName + " does not exist!";
        }
        Student student = studentRepository.findByMatriculationNumber(studentMatriculationNumber).get(0);
        Course course = courseRepository.findByName(courseName).get(0);

        Set<Course> studentCourses = student.getCourses();
        Set<Student> courseStudents = course.getStudents();

        studentCourses.add(course);
        student.setCourses(studentCourses);
        studentRepository.save(student);

        /*courseStudents.add(student);
        course.setStudents(courseStudents);
        courseRepository.save(course);*/

        return "User " + student.getName() + " " + student.getSurname() + " " + student.getMatriculationNumber() + " enrolled in Course " + course.getName();
    }

}
