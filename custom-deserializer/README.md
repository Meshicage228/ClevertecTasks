# Custom realisation of serialization/deserialization

This is my realisation of Jakson serialization/deserialization for Clevertec course. 
This Repository contains source code of mine implementation.


## About Project

* `JsonCustomDeserializer` requires your input `String JSON` and `Class of object` in constructor
* `JsonCustomSerializer` requires only your Object instance in constructor
* Supports `JsonField` annotation : custom realization of `JsonProperty`
* You can generate `String JSON` with Jakson - that will work with `JsonCustomDeserializer` as well
* `JsonCustomDeserializer` can detect invalid json : will throw `InvalidJsonException`
* If field name in json is unlike in Class field, you can use `JsonField` to specify correct field name,
or will be thrown `KeyMismatchException`
* Project contains tests to check efficiency

## Getting Started with Initialization

1. This code will create District object with `deserialize()` method of `JsonCustomDeserializer` instance
```
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

   JsonCustomDeserializer custom = new JsonCustomDeserializer(json, Distric.class);
   District district = custom.deserialize();
```

2. This code will create JSON string with `serialize()` method of `JsonCustomSerializer` 
from District object in example above

```
JsonCustomSerializer checkJsonCreation = new JsonCustomSerializer(district);
String jsonFromObject = checkJsonCreation.serialize();
```

String will be the following : 
```
{
"hospitals":[
{
"yearOfFoundation":1939,
"title":"Hospital"
}
],
"districtName":"Urban District",
"districtStreets":[
{
"streetName":"Broadway",
"sleepingStreet":true
},
{
"streetName":"Park Avenue",
"sleepingStreet":false
}
],
"mainStreet":
{
"streetName":"Big street",
"sleepingStreet":true
},
"schoolCount":10
}
```
### Tests

Project contains base tests to check code works properly

## Thanks for your visiting!