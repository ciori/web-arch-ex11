package it.unitn.mirkomarchiori1.webarchex11.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity(name = "Course")
@Data
public class Course implements Serializable {

    @Id
    private int id;

    private String name;

    @OneToOne(mappedBy = "course")
    private Professor professor;

    @ManyToMany(cascade = {CascadeType.PERSIST}, fetch = FetchType.EAGER, mappedBy = "courses")
    private Set<Student> students;

    @OneToOne(cascade = CascadeType.ALL)
    private Exam exam;

    public Course() {
        this.id = (int) System.nanoTime();
    }

}