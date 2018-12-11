package it.unitn.mirkomarchiori1.webarchex11.model;

import lombok.Data;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("PROFESSOR")
@Data
public class Professor extends Person implements Serializable {

    @OneToOne(cascade={CascadeType.PERSIST})
    private Course course;

    public Professor() {
        super();
    }

}
