package it.unitn.mirkomarchiori1.webarchex11.repository;

import it.unitn.mirkomarchiori1.webarchex11.model.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {

    List<Exam> findByName(String name);

}
