package ru.clevertec;

import ru.clevertec.domain.District;
import ru.clevertec.service.JsonCustomDeserializer;
import ru.clevertec.service.JsonCustomSerializer;

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

        District deserialized = deserialize(json, District.class);

        JsonCustomSerializer checkJsonCreation = new JsonCustomSerializer();
        String jsonFromObject = checkJsonCreation.serialize(deserialized);

        District deserializeFromCustomJson = deserialize(jsonFromObject, District.class);
    }

    private static <T> T deserialize(String json, Class<?> clazz) throws Exception {
        JsonCustomDeserializer checkJsonCreation = new JsonCustomDeserializer();
        return checkJsonCreation.deserialize(json, clazz);
    }
}
