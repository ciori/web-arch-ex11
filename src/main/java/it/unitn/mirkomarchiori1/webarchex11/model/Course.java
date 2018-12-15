package it.unitn.mirkomarchiori1.webarchex11.model;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity(name = "Course")
public class Course implements Serializable {

    private int id;
    private String name;
    private Professor professor;
    private Set<Student> students;
    private Exam exam;

    public Course() {
        this.id = (int) System.nanoTime();
    }

    @Id
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @OneToOne(mappedBy = "course")
    public Professor getProfessor() {
        return this.professor;
    }
    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    @ManyToMany(cascade = {CascadeType.PERSIST}, fetch = FetchType.EAGER, mappedBy = "courses")
    public Set<Student> getStudents() {
        return students;
    }
    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    @OneToOne(cascade = CascadeType.ALL)
    public Exam getExam() {
        return exam;
    }
    public void setExam(Exam exam) {
        this.exam = exam;
    }

}