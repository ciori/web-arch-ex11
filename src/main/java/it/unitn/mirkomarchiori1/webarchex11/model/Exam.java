package it.unitn.mirkomarchiori1.webarchex11.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity(name = "Exam")
public class Exam implements Serializable {

    private int id;
    private Course course;
    private String name;
    private Date date;
    private List<Student> students;
    private List<Integer> grades;

    public Exam() {
        this.id = (int) System.nanoTime();
    }

    @Id
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @OneToOne(cascade = {CascadeType.PERSIST}, mappedBy = "exam")
    public Course getCourse() {
        return this.course;
    }
    public void setCourse(Course course) {
        this.course = course;
    }

    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    @ManyToMany(cascade = {CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinTable(name = "EXAM_STUDENT")
    public List<Student> getStudents() {
        return students;
    }
    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @ElementCollection(targetClass = Integer.class)
    public List<Integer> getGrades() {
        return grades;
    }
    public void setGrades(List<Integer> grades) {
        this.grades = grades;
    }

}