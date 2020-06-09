package ru.volnenko.se.events;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class CommandEvent {
    private final String command;
}
