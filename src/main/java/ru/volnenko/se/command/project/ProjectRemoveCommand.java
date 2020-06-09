package ru.volnenko.se.command.project;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.volnenko.se.command.AbstractCommand;
import ru.volnenko.se.events.CommandEvent;

/**
 * @author Denis Volnenko
 */
@Component
public final class ProjectRemoveCommand extends AbstractCommand {

    @Override
    public String command() {
        return "project-remove";
    }

    @Override
    public String description() {
        return "Remove selected project.";
    }

    @Override
    public void execute() {

    }
    @Override
    @EventListener(condition = "#event.command eq 'project-remove'")
    public void processEvent(CommandEvent event) {
        execute();
    }


}
