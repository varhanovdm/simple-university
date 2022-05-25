package com.test.university.provider;

import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class Provider implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private CommandValidator commandValidator;

    Logger logger = LoggerFactory.getLogger(Provider.class);

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        Scanner scanner = new Scanner(System.in);
        logger.info("\n----------------------------------------------------" +
            "\nWelcome to the University app, enter 'quit' to exit" +
            "\n----------------------------------------------------" +
            "\nAvailable commands:" +
            "\n- Who is head of department {department_name}" +
            "\n- Show {department_name} statistics" +
            "\n- Show the average salary for the department {department_name}" +
            "\n- Show count of employee for {department_name}" +
            "\n- Global search by {template}" +
            "\nType the required command below then press 'Enter'");

        while (true) {
            if (scanner.hasNext("quit")) {
                System.exit(1);
            }
            String string = scanner.nextLine();
            commandValidator.executeCommand(string);
        }
    }

}
