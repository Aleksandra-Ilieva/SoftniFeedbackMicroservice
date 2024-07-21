# Rest Микросървис, за обработка на обратни връзки

## Как се стартира
## Environment variables
За да стартирате проекта, трябва да  добавите следните Environment variables в средата за разбработка:
## Конфигурация за базата данни
````
За връзка с базата данни:
spring.datasource.username=${db_username}
spring.datasource.password=${db_password}
spring.datasource.url=${db_dataUrl}/clinic?useSSL=true&createDatabaseIfNotExist=true
Това е локалния ми url (db_dataUrl-jdbc:mysql://localhost:3306)

````
### Ако няма да изпозлвате MYSQL , моля променете и следните настройки в application.properties:
Трябва да промените настройките за базата, с която ще работите
```
spring.datasource.driverClassName=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/clinic?useSSL=true&createDatabaseIfNotExist=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

```

## Конфигурация на параметъра, който използваме като ключ
В environment variable, в средата за разработка, се слага ключ, който се използва, при свързване с микросъвривса. 
Стойността на ключа се избира от потребителя

````
api.key=${api_key}

````

# Tech stack
#### 1.Java 17

Всички остнали зависимости и библиотеки можете да  намерите в build.gradle

## EndPoints
- GetMapping -> /api/v1/feedback?api_key=(Your key)
- PostMapping -> /api/v1/feedback?api_key=(Your key)
- DeleteMapping -> /api/v1/feedback/{id}?api_key=(Your key)
