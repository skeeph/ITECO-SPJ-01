package ru.volnenko.se.command.data.bin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.volnenko.se.api.service.IProjectService;
import ru.volnenko.se.api.service.ITaskService;
import ru.volnenko.se.command.AbstractCommand;
import ru.volnenko.se.constant.DataConstant;
import ru.volnenko.se.entity.Project;
import ru.volnenko.se.entity.Task;
import ru.volnenko.se.events.CommandEvent;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

/**
 * @author Denis Volnenko
 */
@Component
public final class DataBinaryLoadCommand extends AbstractCommand {
    
    private final IProjectService projectService;
    private final ITaskService taskService;

    @Autowired
    public DataBinaryLoadCommand(IProjectService projectService, ITaskService taskService) {
        this.projectService = projectService;
        this.taskService = taskService;
    }

    @Override
    public String command() {
        return "data-bin-load";
    }

    @Override
    public String description() {
        return "Save data to binary file.";
    }

    @Override
    public void execute() throws Exception {
        System.out.println("[DATA BINARY LOAD]");
        final FileInputStream fileInputStream = new FileInputStream(DataConstant.FILE_BINARY);
        final ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        loadProjects(objectInputStream.readObject());
        loadTasks(objectInputStream.readObject());
        objectInputStream.close();
        fileInputStream.close();
        System.out.println("[OK]");
    }

    @Override
    @EventListener(condition = "#event.command eq 'data-bin-load'")
    public void processEvent(CommandEvent event) throws Exception {
        execute();
    }

    private void loadProjects(final Object value) {
        if (!(value instanceof Project[])) return;
        final Project[] projects = (Project[]) value;
        projectService.load(projects);
    }

    private void loadTasks(final Object value) {
        if (!(value instanceof Task[])) return;
        final Task[] tasks = (Task[]) value;
        taskService.load(tasks);
    }

}
