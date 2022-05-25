package com.test.university.provider;

import java.util.Scanner;
import com.test.university.services.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class Provider implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private UniversityService universityService;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            if (scanner.hasNext("q")) {
                System.exit(1);
            }
            String string = scanner.nextLine();
            executeCommand(string);
        }
    }

    private void executeCommand(String string) {
        if (string.matches("^(Who is head of department [a-zA-Z0-9]+)$")) {
            string = string.substring(26, string.length());
            universityService.showDepartmentHead(string);
            return;
        }
        if (string.matches("^(Show [a-zA-Z0-9]+ statistics[.])$")) {
            string = string.substring(5, string.length() - 12);
            universityService.showDepartmentStatistic(string);
            return;
        }
        if (string.matches("^(Show the average salary for the department [a-zA-Z0-9]+[.])$")) {
            string = string.substring(43, string.length() - 1);
            universityService.showAverageSalary(string);
            return;
        }
        if (string.matches("^(Show count of employee for [a-zA-Z0-9]+[.])$")) {
            string = string.substring(27, string.length() - 1);
            universityService.showCountOfEmployee(string);
            return;
        }
        System.out.println("Invalid command!");
    }
}
