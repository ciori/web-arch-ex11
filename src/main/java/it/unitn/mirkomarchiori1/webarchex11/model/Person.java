package it.unitn.mirkomarchiori1.webarchex11.model;

import lombok.Data;

import java.io.Serializable;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "Person")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DISC", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("PERSON")
@Data
public class Person implements Serializable {

    @Id
    protected int id;

    protected String name;

    protected String surname;

    public Person() {
        this.id = (int) System.nanoTime();
    }

}
