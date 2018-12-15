package it.unitn.mirkomarchiori1.webarchex11.service;

import it.unitn.mirkomarchiori1.webarchex11.model.Course;
import it.unitn.mirkomarchiori1.webarchex11.model.Exam;
import it.unitn.mirkomarchiori1.webarchex11.model.Student;
import it.unitn.mirkomarchiori1.webarchex11.repository.CourseRepository;
import it.unitn.mirkomarchiori1.webarchex11.repository.ExamRepository;
import it.unitn.mirkomarchiori1.webarchex11.repository.ProfessorRepository;
import it.unitn.mirkomarchiori1.webarchex11.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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

    @Transactional(isolation = Isolation.SERIALIZABLE)
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
        studentCourses.add(course);
        student.setCourses(studentCourses);
        studentRepository.save(student);
        return "User " + student.getName() + " " + student.getSurname() + " " + student.getMatriculationNumber() + " enrolled in Course " + course.getName();
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public String registerStudentToExam(Integer studentMatriculationNumber, String examName) {
        if (studentRepository.findByMatriculationNumber(studentMatriculationNumber).size() == 0) {
            return "ERROR: User " + studentMatriculationNumber + " does not exist!";
        }
        if (examRepository.findByName(examName).size() == 0) {
            return "ERROR: Exam for the Course " + examName + " does not exist!";
        }
        Student student = studentRepository.findByMatriculationNumber(studentMatriculationNumber).get(0);
        Exam exam = examRepository.findByName(examName).get(0);
        List<Student> examStudents = exam.getStudents();
        List<Integer> examGrades = exam.getGrades();
        examStudents.add(student);
        examGrades.add(-1);
        exam.setStudents(examStudents);
        exam.setGrades(examGrades);
        examRepository.save(exam);
        return "User " + student.getName() + " " + student.getSurname() + " " + student.getMatriculationNumber() + " registered to Exam of the Course " + exam.getName();
    }

}
