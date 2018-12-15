package it.unitn.mirkomarchiori1.webarchex11.model;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("PROFESSOR")
public class Professor extends Person implements Serializable {

    private Course course;

    public Professor() {
        super();
    }

    @OneToOne(cascade={CascadeType.PERSIST})
    public Course getCourse() {
        return this.course;
    }
    public void setCourse(Course course) {
        this.course = course;
    }

}