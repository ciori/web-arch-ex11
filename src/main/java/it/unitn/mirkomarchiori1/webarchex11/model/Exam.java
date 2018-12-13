package it.unitn.mirkomarchiori1.webarchex11.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

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
@Data
public class Exam implements Serializable {

    @Id
    private int id;

    @OneToOne(cascade = {CascadeType.PERSIST}, mappedBy = "exam")
    private Course course;

    private String name;

    private Date date;

    @ManyToMany(cascade = {CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinTable(name = "EXAM_STUDENT")
    private List<Student> students;

    @ElementCollection(targetClass = Integer.class)
    private List<Integer> grades;

    public Exam() {
        this.id = (int) System.nanoTime();
    }

}
