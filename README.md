# VinylShop API

Een eenvoudige Spring Boot REST API voor het beheren van muziekgenres en uitgevers (publishers), als oefenproject voor NOVI Backend Java.

## Inhoud

- [Projectstructuur](#projectstructuur)
- [Installatie & Setup](#installatie--setup)
- [Database](#database)
- [Testdata](#testdata)
- [API Endpoints](#api-endpoints)
- [Testen met Postman](#testen-met-postman)
- [Extra](#extra)

---

## Projectstructuur

- `controllers/` — REST-controllers voor Genres en Publishers
- `entities/` — JPA entiteiten (BaseEntity, GenreEntity, PublisherEntity)
- `repositories/` — JPA Repositories voor CRUD-functionaliteit
- `services/` — Business logica voor genres en publishers
- `helpers/` — Hulpfuncties, o.a. UrlHelper voor Location headers
- `resources/data.sql` — Testdata voor database
- `resources/postman-collection.json` — Postman requests voor eenvoudig testen

---

## Installatie & Setup

1. **Vereisten**
    - Java 21+ (of gelijk aan de versie in pom.xml)
    - Maven 3.8+
    - PostgreSQL database (lokaal of remote)

2. **Database aanmaken**
    - Start PostgreSQL
    - Maak een database aan, bijvoorbeeld `vinylshop`
    - Zet je gebruikersnaam en wachtwoord in `src/main/resources/application.properties`:

      ```properties
      spring.datasource.url=jdbc:postgresql://localhost:5432/vinylshop
      spring.datasource.username=postgres
      spring.datasource.password=password
      ```

3. **Dependencies installeren en starten**
    ```bash
    mvn clean install
    mvn spring-boot:run
    ```

   De applicatie draait dan op [http://localhost:8080](http://localhost:8080)

---

## Database

- **Tabellen**: genres, publishers
- **Automatisch gevuld** met testdata uit `data.sql` bij eerste start.
- **Datums** worden automatisch gezet via JPA events (`@PrePersist`, `@PreUpdate`).

---

## Testdata

Bij het opstarten worden 5 genres en 3 publishers toegevoegd uit `data.sql`, zoals:

- Genres: Rock, Jazz, Classical, Hip-Hop, Pop
- Publishers: Universal Music Group, Sony Music Entertainment, Warner Music Group

Zie het bestand [`src/main/resources/data.sql`](src/main/resources/data.sql) voor details.

---

## API Endpoints

### Genres

| Methode | Endpoint           | Doel                         |
| ------- | ------------------ | --------------------------- |
| GET     | `/genres`          | Alle genres ophalen          |
| GET     | `/genres/{id}`     | Eén genre ophalen op id      |
| POST    | `/genres`          | Nieuw genre toevoegen        |
| PUT     | `/genres/{id}`     | Genre bijwerken op id        |
| DELETE  | `/genres/{id}`     | Genre verwijderen op id      |

#### Voorbeeld JSON body voor POST/PUT
```json
{
  "name": "Metal",
  "description": "Hard en snel"
}
