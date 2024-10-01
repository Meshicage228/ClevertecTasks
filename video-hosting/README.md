# REST-API web-service : channel video hosting
<a id="readme-top"></a>

# This text documentation of this rest-api

`FULL SWAGGER DOCUMENTATION YOU CAN FIND HERE :` https://meshicage228.github.io/ClevertecTasks/ 

---
# About API models:

1. `User`. User model has the following structure : 
```
    {
        "id" : 11a32b4f-8ff0-4676-830c-986b90a4b7df,  // UUID
        "nickname" : "meshicage",                     // String
        "name" : "Vlad",                              // String
        "email" : "meshicage@yandex.ru"               // string($email)
    }
```
2. `Category`. Category model has the following structure :
```
    {
        "id" : 1,                  // Integer
        "name" : "Film"            // String
    }
```
3. `Channel`. Channel model has the following structure :
```
    {
        "id" : 1,	               // Integer($int64)
        "title" : "Film-channel",      // String
        "author" : 	{              // Object User
               "id" : 11a32b4f-8ff0-4676-830c-986b90a4b7df, 
               "nickname" : "meshicage",                     
               "name" : "Vlad",                             
               "email" : "meshicage@yandex.ru" 
        },
        "subscribers" : [               // Array of Object : User
        	{
        	   "id" : 11a32b4f-8ff0-4676-830c-986b90a4b7df, 
               "nickname" : "meshicage",                     
               "name" : "Vlad",                             
               "email" : "meshicage@yandex.ru" 
        	}
        ], 
        "shortDescription" : "description",  // String
        "creationDate" : "2022-12-12",       // String($date-time)
        "category" : {                       // Object Category
               "id" : 1, 
               "nickname" : "Film",     
        },
        "language" : "RU",                  // String                                 
        "avatar" : (bytes)                  // Byte
    }
```
---
# Getting Started with Endpoints

---
# [`USER GROUP:`](#user-group)

1. [`POST` /v1/users](#post-users)
2. [`PUT` /v1/users/{userId}](#put-users)
3. [`PATCH` /v1/users/{userId}](#patch-users)
4. [`GET` /v1/users/{userId}/subscriptions](#get-users-sub)
5. [`POST` /v1/users/{userId}/subscriptions/{channelId}](#post-users-sub)
6. [`DELETE` /v1/users/{userId}/subscriptions/{channelId}](#delete-users-sub)

---
# [`CHANNEL GROUP:`](#channel-group)

1. [`POST` /v1/channels](#post-channels)
2. [`GET` /v1/channels](#get-channels-filter)
3. [`PUT` /v1/channels/{channelId}](#put-channels)
4. [`PATCH` /v1/channels/{channelId}](#patch-channels)
5. [`GET` /v1/channels/{channelId}](#get-channels)

---
## <p id ="user-group">`User` group endpoints</p>

<a id="post-users">1)</a> `POST` /v1/users

This endpoint creates new user.

`Request Body : `
All fields are required !
```
    {
       "nickname": "meshicage",
       "name": "Vlad",
       "email": "meshicage@yandex.ru"
    }
```

`Responses: `

`201`: user was successfully created : return with generated uuid

Response Example :
```
    {
       "id": "11a32b4f-8ff0-4676-830c-986b90a4b7df",
       "nickname": "meshicage",
       "name": "Vlad",
       "email": "meshicage@yandex.ru"
    }
```
`400`: invalid input

Response Example :
```
    {
       "message": "invalid input"
    }
```  
`409`: that user already exists

Response Example :
```
    {
       "message": "user with that info already exists"
    }
```

<p align="right">(<a href="#readme-top">back to top</a>)</p>

---

<a id = "put-users">2)</a> `PUT` /v1/users/{userId}

First you should introduce path variable : for example `/v1/users/1`
`Request Body : `
   ```
    {
       "nickname": "meshicage",
       "name": "Vlad",
       "email": "meshicage@yandex.ru"
    }
```

`Responses: `

`204`: user was successfully updated

`404`: user with that id wasn't found

Response Example :
```
    {
       "message": ""provided user wasn't found""
    }
```  


`405`: invalid input

Response Example :
```
    {
       "message": "invalid input"
    }
```

`409`: that user already exists

Response Example :
```
    {
       "message": "user with that info already exists"
    }
```

<p align="right">(<a href="#readme-top">back to top</a>)</p>

---

<a id = "patch-users">3)</a> `PATCH` /v1/users/{userId}

Updates user with one of provided field info

First you should introduce path variable : for example `/v1/users/11a32b4f-8ff0-4676-830c-986b90a4b7df`

`Request Body : `
 Use one of that fields to replace existing data
   ```
    {
       "nickname": "meshicage",
       "name": "Vlad",
       "email": "meshicage@yandex.ru"
    }
```

`Responses: `

`204`: user was successfully updated

`404`: user with that id wasn't found

Response Example :
```
    {
       "message": ""provided user wasn't found""
    }
```  


`405`: invalid input

Response Example :
```
    {
       "message": "invalid input"
    }
```

`409`: that user already exists

Response Example :
```
    {
       "message": "user with that info already exists"
    }
```
<p align="right">(<a href="#readme-top">back to top</a>)</p>

---

<a id = "get-users-sub">4)</a> `GET` /v1/users/{userId}/subscriptions

Get the names of all the user's channels to which he is subscribed

First you should introduce path variable : for example `/v1/users/11a32b4f-8ff0-4676-830c-986b90a4b7df`

`200`: info was successfully given

Response Example :
```
    [
       "channel title"
    ]
```  

`403`: access denied due to lack of permissions

```
    {
       "message": "access denied due to lack of permissions"
    }
```

`404`: user with that id wasn't found

Response Example :
```
    {
        "message": "provided user wasn't found"
    }
```
<p align="right">(<a href="#readme-top">back to top</a>)</p>

---

<a id = "post-users-sub">5)</a> `POST` /v1/users/{userId}/subscriptions/{channelId}

Subscribes user to provided channel

First you should introduce {userId} and {channelId} path variable : 

For example `/v1/users/11a32b4f-8ff0-4676-830c-986b90a4b7df/subscriptions/1`

`201`: User subscribed to channel

`403`: Access denied due to lack of permissions

```
     {
        "message": "access denied due to lack of permissions"
     }
```

`404`: Provided channel wasn't found

Response Example :
```
    {
        "message": "provided channel wasn't found"
    }
```

`409`: Provided channel wasn't found

Response Example :
```
    {
       "message": "user already have subscribed"
    }
```
<p align="right">(<a href="#readme-top">back to top</a>)</p>

---

<a id = "delete-users-sub">6)</a> `DELETE` /v1/users/{userId}/subscriptions/{channelId}

Unsubscribes user from provided channel

First you should introduce {userId} and {channelId} path variable :

For example `/v1/users/11a32b4f-8ff0-4676-830c-986b90a4b7df/subscriptions/1`

`204`: channel was successfully unsubscribed

`403`: Access denied due to lack of permissions

```
     {
        "message": "access denied due to lack of permissions"
     }
```

`404`: Provided channel wasn't found

Response Example :
```
    {
        "message": "provided channel wasn't found"
    }
```

<p align="right">(<a href="#readme-top">back to top</a>)</p>

---

## `Channel` group endpoints
<a id = "post-channels">1)</a>`POST` /v1/channels

Creates channel with provided info

`Request Body : `
All fields are required !
```
    {
       "title": "Film review",
       "shortDescription": "Here you can track all latest reviews",
       "category": "Film",
       "language": "RU",
       "avatar": "string"
    }
```

`201`: channel was successfully created

Response Example : returns created channel with id

```
     {
         "id": 10,
         "title": "Film review",
         "author": {
           "id": "11a32b4f-8ff0-4676-830c-986b90a4b7df",
           "nickname": "meshicage",
           "name": "Vlad",
           "email": "meshicage@yandex.ru"
      },
         "subscribers": [
      {
           "id": "11a32b4f-8ff0-4676-830c-986b90a4b7df",
           "nickname": "meshicage",
           "name": "Vlad",
           "email": "meshicage@yandex.ru"
      }
      ],
        "shortDescription": "Here you can track all latest reviews",
        "creationDate": "2022-12-12",
        "category": {
           "id": 1,
           "name": "FILM"
         },
        "language": "RU",
        "avatar": "string"
     }
```

`400`: Invalid input

```
     {
        "message": "invalid input"
     }
```

`403`: Access denied due to lack of permissions

Response Example :
```
    {
        "message": "access denied due to lack of permissions"
    }
```

`409`: That channel already exists

Response Example :
```
    {
        "message": "that channel already exists"
    }
```
<p align="right">(<a href="#readme-top">back to top</a>)</p>

---

<a id = "get-channels-filter">2)</a>`GET` /v1/channels

<a id = "put-channels">3)</a>`PUT` /v1/channels/{channelId}

<a id = "patch-channels">4)</a>`PATCH` /v1/channels/{channelId}

<a id = "get-channels">5)</a>`GET` /v1/channels/{channelId}