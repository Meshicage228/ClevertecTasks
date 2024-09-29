package ru.clevertec.decorator;

import ru.clevertec.db.DataBase;

public abstract class DatabaseDecorator implements DataBase {
    protected DataBase dataBase;

    public DatabaseDecorator(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public void put(String key, String value) {
        dataBase.put(key, value);
    }

    @Override
    public String get(String key) {
        return dataBase.get(key);
    }

    @Override
    public void delete(String key) {
        dataBase.delete(key);
    }
}
