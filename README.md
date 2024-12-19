# Checkout Component REST Service

Checkout REST Service implemented using Java and Spring for calculating total price of the items in cart.

## Features
- Stateful checkout mechanism that can scan items and calculate total price
- Individual item pricing
- Support for multi-item discounts (after getting required quantity of items special prices applied for them)
- Support for combination discounts (bundle pricing)
- Receipt generation with itemized pricing

---
## Getting Started

### Requirements
- Java 19 or higher
- Maven installed

### Build and Run
```bash
mvn clean install
```

### To build JAR
```bash
mvn clean package
```
The JAR file will be created in target folder.

### Run the application using the generated JAR
Open command line and go to project folder and run this command
```bash
java -jar target/checkout-component-3.0.1-SNAPSHOT.jar
```

---

## Design
Service design was first drawn in draw.io and created file is available in properties folder.
```
src/main/resources/readme/checkout-component-design.drawio
```
Below is an image from draw.io of the component checkout service.

<img src="src/main/resources/readme/RestAPI.png" alt="Link to" title="Link to" />

---

## Database

For now implemented only for session time and values are hard coded in demo database version. 
Next steps for improvement are to better the Database design. 
- Further improvements: Add Hibernate or MyBatis
- Install Docker on AWS or Microsoft Azure
- Set up container with chosen database image on Docker

---

## Endpoints
| POST | /api/checkout |
|------|---------------|
