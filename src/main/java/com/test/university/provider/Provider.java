package com.test.university.provider;

import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class Provider implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private CommandValidator commandValidator;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            if (scanner.hasNext("quit")) {
                System.exit(1);
            }
            String string = scanner.nextLine();
            commandValidator.executeCommand(string);
        }
    }

}
