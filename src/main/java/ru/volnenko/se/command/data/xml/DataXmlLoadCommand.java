package ru.volnenko.se.command.data.xml;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.volnenko.se.api.service.IDomainService;
import ru.volnenko.se.command.AbstractCommand;
import ru.volnenko.se.constant.DataConstant;
import ru.volnenko.se.entity.Domain;
import ru.volnenko.se.events.CommandEvent;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

/**
 * @author Denis Volnenko
 */
@Component
public final class DataXmlLoadCommand extends AbstractCommand {
    private final IDomainService domainService;

    @Autowired
    public DataXmlLoadCommand(IDomainService domainService) {
        this.domainService = domainService;
    }

    @Override
    public String command() {
        return "data-xml-load";
    }

    @Override
    public String description() {
        return "Load Domain from XML.";
    }

    @Override
    public void execute() throws Exception {
        System.out.println("[LOAD XML DATA]");
        final File file = new File(DataConstant.FILE_XML);
        if (!exists(file)) return;
        final byte[] bytes = Files.readAllBytes(file.toPath());
        final String json = new String(bytes, StandardCharsets.UTF_8);
        final ObjectMapper objectMapper = new XmlMapper();
        final Domain domain = objectMapper.readValue(json, Domain.class);
        domainService.load(domain);
        System.out.println("[OK]");
    }

    @Override
    @EventListener(condition = "#event.command eq 'data-xml-load'")
    public void processEvent(CommandEvent event) throws Exception {
        execute();
    }


    private boolean exists(final File file) {
        if (file == null) return false;
        final boolean check = file.exists();
        if (!check) System.out.println("FILE NOT FOUND");
        return check;
    }

}
