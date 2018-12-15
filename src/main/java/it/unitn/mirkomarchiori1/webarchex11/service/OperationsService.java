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

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public String assignProfessorToCourse(String professorName, String professorSurname, String courseName) {
        if (professorRepository.findByNameAndSurname(professorName, professorSurname).size() == 0) {
            return "ERROR: Professor " + professorName + " " + professorSurname + " does not exist!";
        }
        if (courseRepository.findByName(courseName).size() == 0) {
            return "ERROR: Course " + courseName + " does not exist!";
        }
        if (courseRepository.findByName(courseName).get(0).getProfessor() != null) {
            return "ERROR: Course " + courseName + " already has a Professor!";
        }
        Professor professor = professorRepository.findByNameAndSurname(professorName, professorSurname).get(0);
        Course course = courseRepository.findByName(courseName).get(0);
        professor.setCourse(course);
        professorRepository.save(professor);
        return "Professor " + professor.getName() + " " + professor.getSurname() + " assigned to Course " + course.getName();
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public String gradeStudent(String professorName, String professorSurname, Integer studentMatriculationNumber, String examName, Integer grade) {
        if (professorRepository.findByNameAndSurname(professorName, professorSurname).size() == 0) {
            return "ERROR: Professor " + professorName + " " + professorSurname + " does not exist!";
        }
        if (studentRepository.findByMatriculationNumber(studentMatriculationNumber).size() == 0) {
            return "ERROR: User " + studentMatriculationNumber + " does not exist!";
        }
        if (examRepository.findByName(examName).size() == 0) {
            return "ERROR: Exam for the Course " + examName + " does not exist!";
        }
        if (professorRepository.findByNameAndSurname(professorName, professorSurname).get(0).getCourse() == null
                || !professorRepository.findByNameAndSurname(professorName, professorSurname).get(0).getCourse().getName().equals(examName)) {
            return "ERROR: Professor " + professorName + " " + professorSurname + " is not the manager for the Course and Exam of " + examName + "!";
        }
        Student student = studentRepository.findByMatriculationNumber(studentMatriculationNumber).get(0);
        Exam exam = examRepository.findByName(examName).get(0);
        List<Student> examStudents = exam.getStudents();
        List<Integer> examGrades = exam.getGrades();
        boolean studentRegistered = false;
        for (Student s: examStudents) {
            if (s.getMatriculationNumber() == studentMatriculationNumber) {
                studentRegistered = true;
            }
        }
        if (!studentRegistered) {
            return "ERROR: User " + student.getName() + " " + student.getSurname() + " " + student.getMatriculationNumber() + " is not registered to the Exam of " + examName + "!";
        }
        for (int i=0;i<examGrades.size();i++) {
            if (examStudents.get(i).getMatriculationNumber() == studentMatriculationNumber) {
                examGrades.set(i, grade);
            }
        }
        exam.setGrades(examGrades);
        examRepository.save(exam);
        return "Assigned grade " + grade + " to the Student " + student.getName() + " " + student.getSurname() + " " + student.getMatriculationNumber() + " for the Exam of " + examName;
    }

}
