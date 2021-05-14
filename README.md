# Deadline

Тестируемая функциональность: 
Автотест, который берёт информацию из БД о сгенерированном коде позволит вам протестировать "Вход в систему" через веб-интерфейс.

Шаги:
1. Запустить Docker Container командой: ```docker-compose up -d```
2. Запустить SUT командой: ```java -jar artifacts/app-deadline.jar -P:jdbc.url=jdbc:mysql://192.168.99.100:3306/app -P:jdbc.user=app -P:jdbc.password=pass```
3. Запуск теста ```gradlew clean test```
