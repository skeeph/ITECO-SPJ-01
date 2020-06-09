package ru.volnenko.se.controller;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import ru.volnenko.se.api.controller.Executor;
import ru.volnenko.se.api.controller.InputReader;
import ru.volnenko.se.events.CommandEvent;

@Controller
public class ExecutorImpl implements Executor {
    private final InputReader inputReader;
    private final ApplicationEventPublisher publisher;

    public ExecutorImpl(InputReader inputReader, ApplicationEventPublisher publisher) {
        this.inputReader = inputReader;
        this.publisher = publisher;
    }

    @Override
    public void start() {
        System.out.println("*** WELCOME TO TASK MANAGER ***");
        String command = "";
        while (!"exit".equals(command)) {
            command = inputReader.nextLine();
            CommandEvent event = new CommandEvent(command);
            publisher.publishEvent(event);
        }
    }

}
