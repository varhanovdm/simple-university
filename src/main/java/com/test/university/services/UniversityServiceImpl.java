package com.test.university.services;

import java.util.List;
import java.util.stream.Collectors;
import com.test.university.data.Department;
import com.test.university.data.Lector;
import com.test.university.data.repository.DepartmentRepository;
import com.test.university.data.repository.LectorRepository;
import com.test.university.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UniversityServiceImpl implements UniversityService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private LectorRepository lectorRepository;

    private static final String DEPARTMENT_NOT_FOUND_FORMAT = "Invalid department name";

    @Override
    public void showDepartmentHeadIfExists(String departmentName) {
        if (checkDepartmentName(departmentName)) {
            Department department = getDepartmentByName(departmentName);
            Lector lector = department.getHeadLector();
            System.out.println(
                "Head of " + department.getName() + " department is " + lector.getFirstName() + " " +
                    lector.getLastName());
        } else {
            System.out.println(DEPARTMENT_NOT_FOUND_FORMAT);
        }
    }

    @Override
    public void showDepartmentStatisticIfExists(String departmentName) {
        if (checkDepartmentName(departmentName)) {
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
                "assistans - " + countAssistant + "\nassociate professors - " + countAssociateProfessor +
                    "\nprofessors - " +
                    countProfessor);
        } else {
            System.out.println(DEPARTMENT_NOT_FOUND_FORMAT);
        }
    }

    @Override
    public void showAverageSalaryIfExists(String departmentName) {
        if (checkDepartmentName(departmentName)) {
            double avg = departmentRepository.avgSalary(departmentName);
            System.out.println("The average salary of " + departmentName + " is " + Math.round(avg * 100.0) / 100.0);
        } else {
            System.out.println(DEPARTMENT_NOT_FOUND_FORMAT);
        }
    }

    @Override
    public void showCountOfEmployeeIfExists(String departmentName) {
        if (checkDepartmentName(departmentName)) {
            Department department = getDepartmentByName(departmentName);
            System.out.println(department.getLectors().size());
        } else {
            System.out.println(DEPARTMENT_NOT_FOUND_FORMAT);
        }

    }

    @Override
    public void globalSearch(String template) {
        List<Lector> lector = lectorRepository.findByTemplate("%" + template + "%");
        if (!lector.isEmpty()) {
            String result =
                lector.stream()
                    .map(l -> l.getFirstName() + " " + l.getLastName())
                    .collect(Collectors.joining(", "));
            System.out.println(result);
        } else {
            System.out.println("Noting found!");
        }

    }

    private boolean checkDepartmentName(String departmentName) {
        return departmentRepository.existsByName(departmentName);
    }

    private Department getDepartmentByName(String departmentName) {
        return departmentRepository.findByName(departmentName)
            .orElseThrow(() -> new NotFoundException("Department not found!"));
    }
}
