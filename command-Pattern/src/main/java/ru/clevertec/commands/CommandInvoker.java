package ru.clevertec.commands;

import ru.clevertec.commands.impl.UpdateProduct;
import ru.clevertec.commands.impl.GetProduct;
import ru.clevertec.commands.impl.SaveProduct;

import java.util.HashMap;
import java.util.Optional;

public class CommandInvoker {
    private static final HashMap<String, Command> commandHashMap = new HashMap<>(){{
        put("get", new GetProduct());
        put("update", new UpdateProduct());
        put("save", new SaveProduct());
    }};

    public Optional<Command> getCommand(String command){
        return Optional.ofNullable(commandHashMap.get(command));
    }

}
