# VinylShop API

Een eenvoudige Spring Boot REST API voor het beheren van muziekgenres en uitgevers (publishers), als oefenproject voor NOVI Backend Java.

## Inhoud

- Projectstructuur
- Installatie & Setup
- Database
- Testdata
- API Endpoints
- Testen met Postman
- Extra

---

## 📁 Projectstructuur

src/main/java/com/vinylWebshop/vinylcollectie/

- controllers/       # REST controllers
- dtos/              # Request + Response DTO’s
- entities/          # JPA entities (BaseEntity included)
- exceptions/        # Error handling
- helpers/           # UrlHelper voor Location headers
- mappers/           # Entity ↔ DTO mappers
- repositories/      # JPA repositories
- services/          # Business services

src/main/resources/

- application.properties
- data.sql
- vinylshop.postman_collection.json

---

## 🚀 Installatie & Setup

### Vereisten
- Java 21+
- Maven 3.8+
- PostgreSQL

### Database configureren

```
CREATE DATABASE vinylshop;
```

In `application.properties`:

```
spring.datasource.url=jdbc:postgresql://localhost:5432/vinylshop
spring.datasource.username=postgres
spring.datasource.password=YOUR_PASSWORD

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.sql.init.mode=always
```

### Project starten

```
mvn clean install
mvn spring-boot:run
```

De API draait op: http://localhost:8080

---

## 🗄️ Database

- genres
- publishers

**BaseEntity bevat:**

- id (auto-generated)
- createDate
- editDate

Automatisch gevuld via `@PrePersist` en `@PreUpdate`.

---

## 🧪 Testdata (data.sql)

Bij opstart worden automatisch 5 genres en 3 publishers toegevoegd.

Genres:
- Rock
- Jazz
- Classical
- Hip-Hop
- Pop

Publishers:
- Universal Music Group
- Sony Music Entertainment
- Warner Music Group

Voorbeeld:

```
INSERT INTO genres (name, description) VALUES ('Rock', 'Hard guitars');
INSERT INTO publishers (name, address, contact_details) VALUES ('Universal Music Group', 'Los Angeles', 'info@umg.com');
```

---

# 🎯 API Endpoints

## 🎵 Genres

| Methode | Endpoint           | Beschrijving |
|---------|--------------------|--------------|
| GET     | /genres            | Alle genres ophalen |
| GET     | /genres/{id}       | Genre op ID ophalen |
| POST    | /genres            | Nieuw genre toevoegen |
| PUT     | /genres/{id}       | Genre bijwerken |
| DELETE  | /genres/{id}       | Genre verwijderen |

### JSON voorbeeld (POST/PUT)

```
{
  "name": "Metal",
  "description": "Hard en snel"
}
```

### Validatie

- name → verplicht, min 2, max 100
- description → max 255

### Voorbeeld validatiefout

```
{
  "name": "Genre name mag niet leeg zijn."
}
```

---

## 📚 Publishers

| Methode | Endpoint              | Beschrijving |
|---------|-----------------------|--------------|
| GET     | /publishers           | Alle publishers |
| GET     | /publishers/{id}      | Publisher op ID |
| POST    | /publishers           | Nieuwe publisher |
| PUT     | /publishers/{id}      | Publisher bijwerken |
| DELETE  | /publishers/{id}      | Publisher verwijderen |

### JSON voorbeeld

```
{
  "name": "New Indie Label",
  "address": "Amsterdam",
  "contactDetails": "info@label.com"
}
```

### Validatie

- name → verplicht, max 50

---

# ⚠️ Error Handling

Globale exception handler:

### Voorbeeld: item niet gevonden

```
DELETE /genres/999
```

Response:

```
{
  "status": 404,
  "error": "Not Found",
  "message": "Genre met id 999 bestaat niet.",
  "path": "/genres/999"
}
```

### Voorbeeld validatiefout

```
{
  "name": "Genre name moet tussen de 2 en 100 karakters lang zijn."
}
```

---

# 🧪 Testen met Postman

Importeer:

```
src/main/resources/vinylshop.postman_collection.json
```

Bevat:

- GET/POST/PUT/DELETE voor genres
- GET/POST/PUT/DELETE voor publishers

Gebruik variabele:

```
{{baseUrl}} = http://localhost:8080
```

---

# ➕ Extra

- DTO's scheiden API-model van database-model
- Mappers zorgen voor gecontroleerde data-transformatie
- UrlHelper genereert correcte Location headers bij POST
- Alles voldoet aan NOVI-opdrachteisen

---