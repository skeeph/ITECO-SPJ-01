package ru.volnenko.se.command.task;

import org.springframework.stereotype.Component;
import ru.volnenko.se.api.controller.InputReader;
import ru.volnenko.se.api.repository.ITaskRepository;
import ru.volnenko.se.command.AbstractCommand;

/**
 * @author Denis Volnenko
 */
@Component
public final class TaskCreateCommand extends AbstractCommand {

    private final InputReader inputReader;
    private final ITaskRepository taskRepository;

    public TaskCreateCommand(InputReader inputReader, ITaskRepository taskRepository) {
        this.inputReader = inputReader;
        this.taskRepository = taskRepository;
    }

    @Override
    public String command() {
        return "task-create";
    }

    @Override
    public String description() {
        return "Create new task.";
    }

    @Override
    public void execute() {
        System.out.println("[TASK CREATE]");
        System.out.println("ENTER NAME:");
        final String name = inputReader.nextLine();
        taskRepository.createTask(name);
        System.out.println("[OK]");
        System.out.println();
    }

}
