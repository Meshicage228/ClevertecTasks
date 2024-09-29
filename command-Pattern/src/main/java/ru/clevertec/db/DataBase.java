package ru.clevertec.db;

public interface DataBase {
    void put(String key, String value);
    String get(String key);
    void delete(String key);
}
