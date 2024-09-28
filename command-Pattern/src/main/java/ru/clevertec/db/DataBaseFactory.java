package ru.clevertec.db;

public class DataBaseFactory {
    private static DataBaseFactory instance;
    private DataBase database;

    private DataBaseFactory() {}

    public static DataBaseFactory getInstance() {
        if (instance == null) {
            instance = new DataBaseFactory();
        }
        return instance;
    }

    public DataBase getDatabase() {
        return database;
    }

    public void setDatabase(DataBase database) {
        this.database = database;
    }
}
