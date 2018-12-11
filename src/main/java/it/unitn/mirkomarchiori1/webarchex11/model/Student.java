package it.unitn.mirkomarchiori1.webarchex11.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
@DiscriminatorValue("STUDENT")
@Data
public class Student extends Person implements Serializable {

    private int matriculationNumber;

    @ManyToMany(cascade = {CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinTable(name = "STUDENT_COURSE")
    private Set<Course> courses;

    public Student() {
        super();
    }

}
