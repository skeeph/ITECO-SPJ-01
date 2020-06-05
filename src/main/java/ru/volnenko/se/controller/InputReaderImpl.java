package ru.volnenko.se.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.volnenko.se.api.controller.InputReader;

import java.util.Scanner;

@Component
public class InputReaderImpl implements InputReader {
    
    private final Scanner scanner;

    @Autowired
    public InputReaderImpl() {
        scanner = new Scanner(System.in);
    }

    @Override
    public String nextLine() {
        return scanner.nextLine();
    }

    @Override
    public Integer nextInteger() {
        final String value = nextLine();
        if (value == null || value.isEmpty()) return null;
        try {
            return Integer.parseInt(value);
        } catch (Exception e) {
            return null;
        }
    }
}
