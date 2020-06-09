package ru.volnenko.se.command.data.bin;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.volnenko.se.command.AbstractCommand;
import ru.volnenko.se.constant.DataConstant;
import ru.volnenko.se.events.CommandEvent;

import java.io.File;
import java.nio.file.Files;

/**
 * @author Denis Volnenko
 */
@Component
public final class DataBinaryClearCommand extends AbstractCommand {

    @Override
    public String command() {
        return "data-bin-clear";
    }

    @Override
    public String description() {
        return "Remove binary data.";
    }

    @Override
    @EventListener(condition = "#event.command eq 'data-bin-clear'")
    public void processEvent(CommandEvent event) throws Exception {
        execute();
    }

    @Override
    public void execute() throws Exception {
        final File file = new File(DataConstant.FILE_BINARY);
        Files.deleteIfExists(file.toPath());
    }

}
