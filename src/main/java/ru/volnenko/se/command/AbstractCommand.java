package ru.volnenko.se.command;

import ru.volnenko.se.events.CommandEvent;

/**
 * @author Denis Volnenko
 */
public abstract class AbstractCommand {

    public abstract void execute() throws Exception;

    public abstract String command();

    public abstract String description();
    
    public abstract void processEvent(CommandEvent event) throws Exception;

}
