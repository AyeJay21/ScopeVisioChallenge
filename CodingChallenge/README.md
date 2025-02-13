# Versicherungsbonus-Berechnung

Dieses Projekt berechnet den Versicherungsbonus basierend auf Fahrzeugtyp, geschätzten jährlichen Kilometern und Region.

### Installation

Voraussetzungen

Java 17

Docker

PostgreSQL

### Lokale Einrichtung

 Repository klonen:

```bash
git clone https://github.com/AyeJay21/ScopeVisioChallenge/tree/main/CodingChallenge
```
Docker-Container starten und bauen:

docker compose up --build

### API-Endpunkt

POST /calculate

Berechnet den Versicherungsbonus basierend auf den folgenden Parametern:

Anfrageparameter:

estimateYearlyKilometer (Double): Muss größer als 0 sein.

vehicleType (String): Einer der folgenden Autotypen:
- CAR
- LIMOUSINE
- SUV
- VAN
- COUPE
- CABRIO
- SMALLCAR

vehicleRegistrationArea (String): Ein Bundesland Deutschlands.

