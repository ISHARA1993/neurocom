
# ✅ 3DS Card Range Lookup Service  

This is a **Spring Boot REST API** that stores and retrieves **3DS Card Range Data** in an H2 in-memory database. It allows:  

✅ Storing multiple card ranges  
✅ Looking up a PAN (Primary Account Number) to find its associated `threeDSMethodURL`  
✅ Returning structured 3DS `Response`  

---

## 🚀 Tech Stack  

- **Java 17**  
- **Spring Boot 3.x**  
- **H2 Database (in-memory)**  
- **Spring Data JPA**  
- **Mockito & JUnit 5 for testing**  

---

## 📦 Project Structure  

```
src/main/java/com/_ds/system/
 ├── controller/        # REST API Controllers
 ├── dto/               # DTOs (Response wrapper)
 ├── model/             # Entity (CardRangeData)
 ├── repository/        # Spring Data JPA Repository
 ├── service/           # Service layer
 └── SystemApplication  # Main Spring Boot app

src/test/java/com/_ds/system/
 ├── controller/        # Controller unit tests
 └── service/           # Service unit tests
```

---

## 🛠️ Setup & Prerequisites  

1️⃣ **Install Java 17+**  
```bash
java -version
```

2️⃣ **Install Maven** (or use wrapper)  
```bash
mvn -v
```

3️⃣ **Clone the repo**  
```bash
git clone https://github.com/your-username/3ds-card-range.git
cd 3ds-card-range
```

---

## ⚙️ Build & Run  

### ✅ Build the project  
```bash
mvn clean install
```

### ✅ Run locally  
```bash
mvn spring-boot:run
```

The application will start at **`http://localhost:8080`**

---

## 🗄️ H2 Database Console  

H2 in-memory database is enabled by default.  

- URL → `http://localhost:8080/h2-console`  
- JDBC URL → `jdbc:h2:mem:testdb`  
- User → `sa`  
- Password → (empty)  

---

## 🔗 API Endpoints  

### ✅ 1. **Store Card Ranges**  

`POST /3ds/card-ranges/store`  

Request Body (JSON Array):  
```json
[
  {
    "startRange": 4000020000000000,
    "endRange": 4000020009999999,
    "threeDSMethodURL": "https://secure4.arcot.com/tds-method"
  },
  {
    "startRange": 4000090000000000,
    "endRange": 4000099999999999,
    "threeDSMethodURL": "https://geoissuer.cardinalcommerce.com/RenderMethodURL"
  }
]
```

✅ **Response 200 OK**  
```
Card ranges stored successfully in H2 DB!
```

✅ **Response 204 No Content** (if empty input)  

---

### ✅ 2. **Lookup PAN**  

`GET /3ds/card-ranges/lookup/{pan}`  

Example:  
```
GET /3ds/card-ranges/lookup/4000020000000123
```

✅ **Response 200 OK** (if found)  

```json
{
  "serialNum": "5780074",
  "messageType": "PAN :4000020000000123",
  "dsTransID": "9f05c4e2-e3a2-4127-b3f6-e9957a2c597d",
  "messageVersion": "2.2.0",
  "dsEndProtocolVersion": "2.2.0",
  "dsStartProtocolVersion": "2.1.0",
  "threeDSServerTransID": "12677a32-97fb-4c0d-a67b-44f2e5bcc172",
  "o": {
    "id": 1,
    "startRange": 4000020000000000,
    "endRange": 4000020009999999,
    "threeDSMethodURL": "https://secure4.arcot.com/tds-method"
  }
}
```

✅ **Response 404 Not Found**  
```
(no body)
```

---

## 🧪 Running Tests  

Run all tests:  

```bash
mvn test
```

It includes:  
✅ Controller unit tests with MockMvc  
✅ Service unit tests with Mockito  

---

## 📝 Example Test Data  

A larger sample JSON (50+ ranges) is available for bulk testing.  

---
