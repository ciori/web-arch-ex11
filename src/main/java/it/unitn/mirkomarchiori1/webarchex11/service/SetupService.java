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

@Service
public class SetupService {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ProfessorRepository professorRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private ExamRepository examRepository;

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public String createStudent(Student student) {
        studentRepository.save(student);
        Student resStudent = studentRepository.findByMatriculationNumber(student.getMatriculationNumber()).get(0);
        return "User " + resStudent.getName() + " " + resStudent.getSurname() + " " + resStudent.getMatriculationNumber() + " created";
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public String createProfessor(Professor professor) {
        professorRepository.save(professor);
        Professor resProfessor = professorRepository.findByNameAndSurname(professor.getName(), professor.getSurname()).get(0);
        return "Professor " + resProfessor.getName() + " " + resProfessor.getSurname() + " created";
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public String createCourse(Course course) {
        courseRepository.save(course);
        Course resCourse = courseRepository.findByName(course.getName()).get(0);
        return "Course " + resCourse.getName() + " created";
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public String createExam(Exam exam) {
        if (courseRepository.findByName(exam.getName()).size() == 0) {
            return "No available Course named " + exam.getName();
        }
        Course course = courseRepository.findByName(exam.getName()).get(0);
        course.setExam(exam);
        courseRepository.save(course);
        Exam resExam = examRepository.findByName(exam.getName()).get(0);
        return "Exam for Course " + resExam.getName() + " on date " + resExam.getDate().toString() + " created";
    }

}
