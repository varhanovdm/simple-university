package com.test.university.services;

public interface UniversityService {
    void showDepartmentHeadIfExists(String departmentName);

    void showDepartmentStatisticIfExists(String departmentName);

    void showAverageSalaryIfExists(String departmentName);

    void showCountOfEmployeeIfExists(String departmentName);

    void globalSearch(String template);
}
