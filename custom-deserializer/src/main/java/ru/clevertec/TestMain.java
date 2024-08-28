package ru.clevertec;

import ru.clevertec.domain.District;
import ru.clevertec.service.JsonCustomDeserializer;

public class TestMain {
    public static void main(String[] args) throws Exception {
        String json = """
                {
                  "districtName": "Urban District",
                  "mainStreet: {
                      "streetName" : "Big street",
                      "sleepingStreet": true
                  },
                  "hospitals": [
                    {
                      "title": "Hospital",
                      "yearOfFoundation" : 1939
                    }
                  ],
                  "districtStreets": [
                    {
                      "streetName": "Broadway",
                      "sleepingStreet": true
                    },
                    {
                      "streetName": "Park Avenue",
                      "sleepingStreet": false
                    }
                  ],
                  "schoolCount": 10
                }""";

        JsonCustomDeserializer jsonCustomDeserializer = new JsonCustomDeserializer(json, District.class);
        District deserialized = jsonCustomDeserializer.deserialize();
        System.out.println(deserialized);
    }
}
