package ru.volnenko.se.command.task;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.volnenko.se.api.controller.InputReader;
import ru.volnenko.se.command.AbstractCommand;
import ru.volnenko.se.events.CommandEvent;

/**
 * @author Denis Volnenko
 */
@Component
public final class TaskRemoveCommand extends AbstractCommand {
    private final InputReader inputReader;

    public TaskRemoveCommand(InputReader inputReader) {
        this.inputReader = inputReader;
    }

    @Override
    public String command() {
        return "task-remove";
    }

    @Override
    public String description() {
        return "Remove selected task.";
    }

    @Override
    public void execute() {
        System.out.println("[REMOVING TASK]");
        System.out.println("Enter task order index:");
        final Integer orderIndex = inputReader.nextInteger();
        if (orderIndex == null) {
            System.out.println("Error! Incorrect order index...");
            System.out.println();
            return;
        }
        System.out.println("[OK]");
    }

    @Override
    @EventListener(condition = "#event.command eq 'task-remove'")
    public void processEvent(CommandEvent event) {
        execute();
    }

}
