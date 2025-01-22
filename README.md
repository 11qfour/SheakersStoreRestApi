# 🏷️ Sneakers Store API

![Java](https://img.shields.io/badge/Java-17+-green?style=flat-square&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.0.0+-brightgreen?style=flat-square&logo=spring)
![Thymeleaf](https://img.shields.io/badge/Thymeleaf-%23blue?style=flat-square&logo=thymeleaf)

---

## 📚 Описание проекта

**Sneakers Store API** — это веб-приложение для управления данными о кроссовках и их поставщиках. Оно позволяет:
- Добавлять, удалять и редактировать кроссовки.
- Управлять данными о поставщиках.
- Связывать кроссовки с поставщиками через удобный интерфейс.

Приложение разработано на **Spring Boot** с использованием **Thymeleaf** для отображения данных на HTML-страницах.

---

## 🚀 Функционал

- Просмотр списка кроссовок.
- Добавление новых кроссовок.
- Редактирование и удаление существующих данных.
- Привязка поставщиков к кроссовкам.
- Отображение HTML-форм для взаимодействия с данными.

---

## 🛠️ Технологии

- **Backend**: Spring Boot, Spring Data JPA, Hibernate.
- **Frontend**: HTML, CSS, Thymeleaf.
- **База данных**: H2 (по умолчанию), поддержка MySQL/PostgreSQL.
- **Инструменты разработки**: Maven, IntelliJ IDEA.

---

## 📂 Структура проекта
src/ 
├── main/ 
│├── java/ 
│ │ └── com.example.demo/ 
│ │ ├── controller/ # Контроллеры для обработки запросов 
│ │ ├── dto/ # DTO для обмена данными │ │ ├── entity/ # JPA-сущности 
│ │ ├── repository/ # Репозитории для работы с базой данных 
│ │ ├── service/ # Бизнес-логика 
│ │ └── DemoApplication.java # Главный класс запуска 
│ └── resources/ │ ├── templates/ # HTML-шаблоны (Thymeleaf) 
│ ├── application.properties # Настройки приложения 
│ └── static/ # CSS и другие статические файлы └── test/ # Тесты

---

## 🔧 Установка и запуск

1. **Склонируйте репозиторий**:
   ```bash
   git clone https://github.com/11qfour/SheakersStoreRestApi
   cd repo-folder

2. **Настройте базу данных: По умолчанию используется H2. Чтобы подключить MySQL/PostgreSQL, обновите application.properties:**
    spring.datasource.url=jdbc:mysql://localhost:3306/sneakers_db
    spring.datasource.username=root
    spring.datasource.password=your_password
3. **Запустите приложение:**
    mvn spring-boot:run
    mvn spring-boot:run
4. **Откройте в браузере:**
     http://localhost:8080/sneakers

🖼️ Интерфейс [в разработке]

* Список кроссовок

* Добавление новых кроссовок

🧪 Тестирование

* Тестирование программы при помощи curl-запросов

Для проверки работы API можно использовать следующие `cURL`-запросы. Примеры запросов ниже упрощены и разделены по категориям.

---

### 🛒 Customers
1. **Создание клиента:**
    ```bash
    curl -X POST http://localhost:8080/api/customers \
    -H "Content-Type: application/json" \
    -d "{\"firstName\": \"Oleg\",\"lastName\":\"Milkovich\", \"patronymic\":\"Vasilevich\", \"contactInfo\": {\"email\": \"milkovich@example.com\", \"phone\": \"1234567890\", \"age\": 30}}"
    ```

2. **Получение всех клиентов:**
    ```bash
    curl -X GET http://localhost:8080/api/customers
    ```

3. **Обновление данных клиента:**
    ```bash
    curl -X PUT http://localhost:8080/api/customers/1 \
    -H "Content-Type: application/json" \
    -d "{\"firstName\": \"kolenvalov\",  \"lastName\": \"alexander\",  \"patronymic\":  \"-\", \"contactInfo\": {\"email\": \"no@gmail.com\",\"phone\": \"32778123123\", \"age\": 22 }}"
    ```

4. **Удаление клиента:**
    ```bash
    curl -X DELETE http://localhost:8080/api/customers/1
    ```

---

### 👟 Sneakers
1. **Создание кроссовок:**
    ```bash
    curl -X POST http://localhost:8080/api/sneakers \
    -H "Content-Type: application/json" \
    -d "{\"sneakerModel\": \"Nike Initiator\", \"size\": 41.0, \"price\": 15000, \"sneakersTypeId\": 1, \"supplierIds\": [1, 2]}"
    ```

2. **Получение всех кроссовок:**
    ```bash
    curl -X GET http://localhost:8080/api/sneakers
    ```

3. **Обновление данных кроссовок:**
    ```bash
    curl -X PUT http://localhost:8080/api/sneakers/1 \
    -H "Content-Type: application/json" \
    -d "{\"sneakerModel\": \"Vans\", \"size\": 42.0, \"price\": 13000, \"sneakersTypeId\": 2, \"supplierIds\": [3, 4]}"
    ```

4. **Удаление кроссовок:**
    ```bash
    curl -X DELETE http://localhost:8080/api/sneakers/1
    ```

---

### 🏷️ Sneakers Type
1. **Создание типа кроссовок:**
    ```bash
    curl -X POST http://localhost:8080/api/sneakers/type \
    -H "Content-Type: application/json" \
    -d "{\"name\": \"Running\", \"description\": \"For running\", \"sneakers\": []}"
    ```

2. **Получение всех типов кроссовок:**
    ```bash
    curl -X GET http://localhost:8080/api/sneakers/type
    ```

3. **Обновление типа кроссовок:**
    ```bash
    curl -X PUT http://localhost:8080/api/sneakers/type/1 \
    -H "Content-Type: application/json" \
    -d "{\"name\": \"Walking\", \"description\": \"Updated description\", \"sneakers\": []}"
    ```

4. **Удаление типа кроссовок:**
    ```bash
    curl -X DELETE http://localhost:8080/api/sneakers/type/1
    ```

---

### 🏪 Suppliers
1. **Создание поставщика:**
    ```bash
    curl -X POST http://localhost:8080/api/supplier \
    -H "Content-Type: application/json" \
    -d "{\"name\": \"Vamp\", \"contactNumber\": \"2345678901\", \"sneakers\": []}"
    ```

2. **Получение всех поставщиков:**
    ```bash
    curl -X GET http://localhost:8080/api/supplier
    ```

3. **Обновление поставщика:**
    ```bash
    curl -X PUT http://localhost:8080/api/supplier/2 \
    -H "Content-Type: application/json" \
    -d "{\"name\": \"Vamp Updated\", \"contactNumber\": \"2435678901\", \"sneakers\": []}"
    ```

4. **Удаление поставщика:**
    ```bash
    curl -X DELETE http://localhost:8080/api/supplier/1
    ```

---

### 📸 Скриншоты выполнения запросов

Ниже приведены скриншоты выполнения вышеуказанных `cURL`-запросов в терминале:

#### Создание клиента
![Создание клиента](https://github.com/11qfour/HoroscopePredsazTGBot/blob/src/main/resources/media/customer.png)

#### Получение всех кроссовок
![Список кроссовок](https://github.com/11qfour/HoroscopePredsazTGBot/blob/src/main/resources/media/sneakers.png)

#### Обновление типа кроссовок
![Обновление типа кроссовок](https://github.com/11qfour/HoroscopePredsazTGBot/blob/src/main/resources/media/sneakerstype.png)

#### Создание поставщика
![Создание поставщика](https://github.com/11qfour/HoroscopePredsazTGBot/blob/src/main/resources/media/supplier.png)

---

* Тестирование программы при помощи Swagger

![Контроллеры](https://github.com/11qfour/HoroscopePredsazTGBot/blob/src/main/resources/media/swaggerConroller.png)

✉️ Контакты

Email: elevenfourprod@yandex.ru
GitHub: @11qfour