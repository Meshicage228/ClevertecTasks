package ru.clevertec.decorator;

import ru.clevertec.db.DataBase;

import java.util.HashMap;
import java.util.Map;

public class CashingDecorator extends DatabaseDecorator {
    public Map<String, String> cache;

    public CashingDecorator(DataBase database) {
        super(database);
        cache = new HashMap<>();
    }

    @Override
    public void put(String key, String value) {
        dataBase.put(key, value);
        cache.put(key, value);
    }

    @Override
    public String get(String key) {
        String value = cache.get(key);
        if (value != null) {
            return value;
        }
        value = dataBase.get(key);
        cache.put(key, value);
        return value;
    }

    @Override
    public void delete(String key) {
        dataBase.delete(key);
        cache.remove(key);
    }
}
