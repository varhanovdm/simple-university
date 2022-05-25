package com.test.university.data.repository;

import java.util.List;
import com.test.university.data.Lector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LectorRepository extends JpaRepository<Lector, Long> {

    @Query(value = "SELECT lector FROM Lector lector WHERE lector.firstName LIKE :template OR lector.lastName LIKE :template")
    List<Lector> findByTemplate(String template);
}
