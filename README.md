# REST Микросървис за Обработка на Обратни Връзки



# Tech stack
#### 1.Java 17
#### 2. gradle
Всички останали зависимости и библиотеки могат да се видят в `build.gradle` файла.

## Конфигурация и Стартиране

1. **Конфигурирайте Environment Variables**:
   За стартиране на проекта са нужни следните environment variables, отговарящи на съответните конфигурации в [application.properties](src/main/resources/application.properties):
- `db_username` -> `spring.datasource.username`
- `db_password` -> `spring.datasource.password`
- `db_dataUrl` -> `spring.datasource.url`
- `api_key` -> `api.key`

## Конфигурация за базата данни
````
spring.datasource.username=${db_username}
spring.datasource.password=${db_password}
spring.datasource.url=${db_dataUrl}/clinic?useSSL=true&createDatabaseIfNotExist=true
````
Пример за локален url (db_dataUrl-jdbc:mysql://localhost:3306)
Ако не се използва MySQL, е необходимо да се променят следните настройки в application.properties, за да съответстват на базата данни, която се използва:

```
spring.datasource.driverClassName
spring.datasource.url
spring.jpa.properties.hibernate.dialect
```

## Конфигурация на Ключа за Свързване
Добавя се ключ за свързване с микросървиса в environment variables. Стойността на ключа се избира от потребителя.
````
api.key=${api_key}
````

## Endpoints


- **GET** `/api/v1/feedback?api_key={Your key}`
    - **Описание**: Връща списък с всички обратни връзки.
    - **Параметри**:
        - `api_key`: (задължителен) Ключ за достъп, предоставен в environment variables.
- **POST** `/api/v1/feedback?api_key={Your key}`
    - **Описание**: Създава нова обратна връзка.
  - - **Параметри**:
          - `api_key`: (задължителен) Ключ за достъп, предоставен в environment variables.
- **DELETE** `/api/v1/feedback/{id}?api_key={Your key}`
    - **Описание**: Изтрива обратна връзка по зададено ID.
    - **Параметри**:
        - `id`: (задължителен) ID на обратната връзка, която трябва да бъде изтрита.
        - `api_key`: (задължителен) Ключ за достъп, предоставен в environment variables.
