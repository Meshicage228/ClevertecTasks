package ru.clevertec.commands;

import ru.clevertec.commands.impl.UpdateProduct;
import ru.clevertec.commands.impl.GetProduct;
import ru.clevertec.commands.impl.SaveProduct;
import ru.clevertec.proxy.realisation.GetProductProxy;
import ru.clevertec.proxy.realisation.SaveProductProxy;
import ru.clevertec.proxy.realisation.UpdateProductProxy;

import java.util.HashMap;
import java.util.Optional;

public class CommandInvoker {
    private static final HashMap<String, Command> commandHashMap = new HashMap<>(){{
        put("get", new GetProductProxy(new GetProduct()));
        put("update", new UpdateProductProxy(new UpdateProduct()));
        put("save", new SaveProductProxy(new SaveProduct()));
    }};

    public Optional<Command> getCommand(String command){
        return Optional.ofNullable(commandHashMap.get(command));
    }

}
