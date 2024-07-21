# REST Микросървис за Обработка на Обратни Връзки

## Как се Стартира

1. **Конфигурирайте Environment Variables**:
   За да стартирате микросървиса, е необходимо да добавите следните environment variables в средата за разработка.
- `spring.datasource.username=${db_username}`
- `spring.datasource.password=${db_password}`
- `spring.datasource.url=${db_dataUrl}/clinic?useSSL=true&createDatabaseIfNotExist=true`
- `api.key=${api_key}`
## Конфигурация за базата данни
````
spring.datasource.username=${db_username}
spring.datasource.password=${db_password}
spring.datasource.url=${db_dataUrl}/clinic?useSSL=true&createDatabaseIfNotExist=true

````
Пример за локален url (db_dataUrl-jdbc:mysql://localhost:3306)
### Ако не използвате MySQL, променете следните настройки в application.properties:

```
spring.datasource.driverClassName=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/clinic?useSSL=true&createDatabaseIfNotExist=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

```

## Конфигурация на Ключа за Свързване
Добавете ключ за свързване с микросървиса в environment variables. Стойността на ключа се избира от потребителя:

````
api.key=${api_key}

````

# Tech stack
#### 1.Java 17

Останалите зависимости и библиотеки се намират в build.gradle.

## Endpoints

### Получаване на Обратни Връзки

- **GET** `/api/v1/feedback?api_key={Your key}`
    - **Описание**: Връща списък с всички обратни връзки.
    - **Параметри**:
        - `api_key`: (задължителен) Ключ за достъп, предоставен в environment variables.
- **POST** `/api/v1/feedback?api_key={Your key}`
    - **Описание**: Създава нова обратна връзка.
    - - **Тяло на заявката**: Обратната връзка трябва да бъде предоставена в JSON формат.
- **DELETE** `/api/v1/feedback/{id}?api_key={Your key}`
    - **Описание**: Изтрива обратна връзка по зададено ID.
    - **Параметри**:
        - `id`: (задължителен) ID на обратната връзка, която трябва да бъде изтрита.
        - `api_key`: (задължителен) Ключ за достъп, предоставен в environment variables.
