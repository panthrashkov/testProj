Тестовое задание
======================
Репозиторий
-
git - https://github.com/panthrashkov/testProj.git

Сборка образа
 mvn package docker:build
 Запуск образа
docker run -e "SPRING_PROFILES_ACTIVE=development" -p 8888:8888 -t rencredit/test

Использовать Swagger UI
-
Пример обращения http://localhost:8888//swagger-ui.html
пользователь u пароль u
Проверка состояния http://localhost:8888/actuator/health
Логи http://localhost:8888/actuator/httptrace