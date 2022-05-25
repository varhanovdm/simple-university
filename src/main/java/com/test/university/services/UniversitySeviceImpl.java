package com.test.university.services;

import com.test.university.data.Department;
import com.test.university.data.Lector;
import com.test.university.data.repository.DepartmentRepository;
import com.test.university.data.repository.LectorRepository;
import com.test.university.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UniversitySeviceImpl implements UniversityService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private LectorRepository lectorRepository;

    @Override
    public void showDepartmentHead(String departmentName) {
        Department department = getDepartmentByName(departmentName);
        Lector lector = department.getHeadLector();
        System.out.println(
            "Head of " + department.getName() + " department is " + lector.getLastName() + " " + lector.getFirstName());
    }

    @Override
    public void showDepartmentStatistic(String departmentName) {
        Department department = getDepartmentByName(departmentName);
        long countAssistant =
            department.getLectors().stream().filter(lector -> Lector.Degree.ASSISTANT.equals(lector.getDegree()))
                .count();
        long countAssociateProfessor =
            department.getLectors().stream()
                .filter(lector -> Lector.Degree.ASSOCIATE_PROFESSOR.equals(lector.getDegree()))
                .count();
        long countProfessor =
            department.getLectors().stream().filter(lector -> Lector.Degree.PROFESSOR.equals(lector.getDegree()))
                .count();
        System.out.println(
            "Assistans - " + countAssistant + "\nAssociateProfessor - " + countAssociateProfessor + "\nProfessor - " +
                countProfessor);
    }

    @Override
    public void showAverageSalary(String departmentName) {
        double avg = departmentRepository.avgSalary(departmentName);
        System.out.println("The average salary of " + departmentName + " is " + Math.round(avg * 100.0) / 100.0);
    }

    @Override
    public void showCountOfEmployee(String departmentName) {
        Department department = getDepartmentByName(departmentName);
        System.out.println(department.getLectors().size());
    }

    private Department getDepartmentByName(String departmentName) {
        return departmentRepository.findByName(departmentName)
            .orElseThrow(() -> new NotFoundException("Department not found!"));
    }


}
