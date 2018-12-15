package it.unitn.mirkomarchiori1.webarchex11.repository;

import it.unitn.mirkomarchiori1.webarchex11.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findByName(String name);

}
