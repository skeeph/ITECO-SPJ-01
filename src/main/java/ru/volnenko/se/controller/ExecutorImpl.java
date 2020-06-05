package ru.volnenko.se.controller;

import org.springframework.stereotype.Controller;
import ru.volnenko.se.api.controller.Executor;
import ru.volnenko.se.api.controller.InputReader;
import ru.volnenko.se.command.AbstractCommand;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ExecutorImpl implements Executor {
    private final InputReader inputReader;
    private final Map<String, AbstractCommand> commands;

    public ExecutorImpl(InputReader inputReader, List<? extends AbstractCommand> commandsList) {
        this.inputReader = inputReader;
        commands = new HashMap<>();
        for (AbstractCommand abstractCommand : commandsList) {
            commands.put(abstractCommand.command(), abstractCommand);
        }
    }

    @Override
    public void start() throws Exception {
        System.out.println("*** WELCOME TO TASK MANAGER ***");
        String command = "";
        while (!"exit".equals(command)) {
            command = inputReader.nextLine();
            execute(command);
        }
    }

    private void execute(final String command) throws Exception {
        if (command == null || command.isEmpty()) return;
        final AbstractCommand abstractCommand = commands.get(command);
        if (abstractCommand == null) return;
        abstractCommand.execute();
    }
}
