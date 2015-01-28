# Quote Web App

## Установка:

1) Клонировать репозитарий
```
git clone https://github.com/raibovdenis/quotewebapp.git
```
2) Создать базу данных и пользователя

В mysql создать базу данных "quotewebapp" и пользователя "root" с пустым паролем. Назначить права для пользовотеля.
Настройки для базы данных можно поменять в файле src/main/resources/database.properties

3) Запустить проект
```
mvn clean package
mvn tomcat7:run
```
4) Проект будет доступен по адресу [http://127.0.0.1:8080/quotewebapp/](http://127.0.0.1:8080/quotewebapp/)