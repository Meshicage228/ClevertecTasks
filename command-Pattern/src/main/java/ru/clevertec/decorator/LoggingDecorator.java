package ru.clevertec.decorator;

import ru.clevertec.db.DataBase;

public class LoggingDecorator extends DatabaseDecorator {
    public LoggingDecorator(DataBase dataBase) {
        super(dataBase);
    }

    @Override
    public void put(String key, String value) {
        System.out.println("Putting key-value pair: " + key + " = " + value);
        dataBase.put(key, value);
    }

    @Override
    public String get(String key) {
        System.out.println("Getting value for key: " + key);
        return dataBase.get(key);
    }

    @Override
    public void delete(String key) {
        System.out.println("Deleting key: " + key);
        dataBase.delete(key);
    }
}
