package it.unitn.mirkomarchiori1.webarchex11.repository;

import it.unitn.mirkomarchiori1.webarchex11.model.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {

    List<Student> findByMatriculationNumber(int matriculationNumber);

}
