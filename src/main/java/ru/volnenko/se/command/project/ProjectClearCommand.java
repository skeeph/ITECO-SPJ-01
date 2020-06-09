package ru.volnenko.se.command.project;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.volnenko.se.api.repository.IProjectRepository;
import ru.volnenko.se.command.AbstractCommand;
import ru.volnenko.se.events.CommandEvent;

/**
 * @author Denis Volnenko
 */
@Component
public final class ProjectClearCommand extends AbstractCommand {
    private final IProjectRepository projectRepository;

    public ProjectClearCommand(IProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public String command() {
        return "project-clear";
    }

    @Override
    public String description() {
        return "Remove all projects.";
    }

    @Override
    public void execute() {
        projectRepository.clear();
        System.out.println("[ALL PROJECTS REMOVED]");
    }

    @Override
    @EventListener(condition = "#event.command eq 'project_clear'")
    public void processEvent(CommandEvent event) {
        execute();
    }


}
