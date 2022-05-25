package com.test.university.provider;

import com.test.university.services.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommandValidator {

    @Autowired
    private UniversityService universityService;

    public void executeCommand(String string) {
        if (string.matches("^(Who is head of department [a-zA-Z0-9]+)$")) {
            string = string.substring(26);
            universityService.showDepartmentHeadIfExists(string);
            return;
        }
        if (string.matches("^(Show [a-zA-Z0-9]+ statistics)$")) {
            string = string.substring(5, string.length() - 11);
            universityService.showDepartmentStatisticIfExists(string);
            return;
        }
        if (string.matches("^(Show the average salary for the department [a-zA-Z0-9]+)$")) {
            string = string.substring(43);
            universityService.showAverageSalaryIfExists(string);
            return;
        }
        if (string.matches("^(Show count of employee for [a-zA-Z0-9]+)$")) {
            string = string.substring(27);
            universityService.showCountOfEmployeeIfExists(string);
            return;
        }
        if (string.matches("^(Global search by [a-zA-Z0-9]+)$")) {
            string = string.substring(17);
            universityService.globalSearch(string);
            return;
        }
        System.out.println("Invalid command!");
    }
}
