package it.unitn.mirkomarchiori1.webarchex11.repository;

import it.unitn.mirkomarchiori1.webarchex11.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {

    List<Professor> findByNameAndSurname(String name, String surname);

}
