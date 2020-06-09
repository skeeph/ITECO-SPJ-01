package ru.volnenko.se.command.project;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.volnenko.se.api.controller.InputReader;
import ru.volnenko.se.api.repository.IProjectRepository;
import ru.volnenko.se.command.AbstractCommand;
import ru.volnenko.se.events.CommandEvent;

/**
 * @author Denis Volnenko
 */
@Component
public final class ProjectCreateCommand extends AbstractCommand {
    private final InputReader reader;
    private final IProjectRepository projectRepository;

    public ProjectCreateCommand(InputReader reader, IProjectRepository projectRepository) {
        this.reader = reader;
        this.projectRepository = projectRepository;
    }

    @Override
    public String description() {
        return "Create new project.";
    }

    @Override
    public String command() {
        return "project-create";
    }

    @Override
    public void execute() {
        System.out.println("[PROJECT CREATE]");
        System.out.println("ENTER NAME:");
        final String name = reader.nextLine();
        projectRepository.createProject(name);
        System.out.println("[OK]");
        System.out.println();
    }

    @Override
    @EventListener(condition = "#event.command eq 'project-create'")
    public void processEvent(CommandEvent event) {
        execute();
    }


}
