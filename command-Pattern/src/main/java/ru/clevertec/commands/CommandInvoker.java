package ru.clevertec.commands;

import ru.clevertec.commands.impl.CloseCommand;
import ru.clevertec.commands.impl.OpenCommand;
import ru.clevertec.commands.impl.SaveCommand;

import java.util.HashMap;

public class CommandInvoker {
    private static final HashMap<String, Command> commandHashMap = new HashMap<>(){{
        put("open", new OpenCommand());
        put("close", new CloseCommand());
        put("save", new SaveCommand());
    }};
    public void invoke(String command){
        commandHashMap.get(command).doSmth();
    }

}
