package ru.clevertec;

import ru.clevertec.domain.District;
import ru.clevertec.service.JsonCustomDeserializer;

public class TestMain {
    public static void main(String[] args) throws Exception {
        String json = """
                {
                  "districtName": "Urban District",
                  "mainStreet: {
                      "name" : "Big street",
                      "isSleeping": true
                  },
                  "streets": [
                    {
                      "name": "Broadway",
                      "isSleeping": true
                    },
                    {
                      "name": "Park Avenue",
                      "isSleeping": false
                    }
                  ],
                  "schools": 10
                }""";

        JsonCustomDeserializer jsonCustomDeserializer = new JsonCustomDeserializer(json, District.class);
        District deserialized = jsonCustomDeserializer.deserialize();
        System.out.println(deserialized);
    }
}
