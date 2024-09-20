package ru.clevertec.db;

import ru.clevertec.decorator.CashingDecorator;
import ru.clevertec.decorator.LoggingDecorator;

public class SingletonDataBase {
    private static DataBase database;

    public static DataBase getDatabase() {
        if (database == null) {
            database = new LoggingDecorator(
                    new CashingDecorator(
                            new InMemoryDataBase()
                    )
            );
        }
        return database;
    }
}
