package com.test.university.data.repository;

import java.util.Optional;
import com.test.university.data.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    Optional<Department> findByName(String departmentName);

    @Query(value = "SELECT avg(lector.salary) FROM Department department LEFT JOIN department.lectors lector WHERE department.name = :departmentName")
    Double avgSalary(String departmentName);

}
