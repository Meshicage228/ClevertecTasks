package ru.clevertec.db;

import java.util.HashMap;
import java.util.Map;

public class InMemoryDataBase implements DataBase {
    private final Map<String, String> data;

    public InMemoryDataBase() {
        data = new HashMap<>();
    }

    @Override
    public void put(String key, String value) {
        data.put(key, value);
    }

    @Override
    public String get(String key) {
        return data.get(key);
    }

    @Override
    public void delete(String key) {
        data.remove(key);
    }
}
