package ru.volnenko.se;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.volnenko.se.config.AppConfiguration;
import ru.volnenko.se.api.controller.Executor;

public class App {

    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        Executor executor = context.getBean(Executor.class);
        executor.start();
    }

}
