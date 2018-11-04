# Compromises validator

Import one or many bank compromises in parallel process.

## Setup

### Requirements

- [Apache Kafka](https://kafka.apache.org/downloads)
- [Java](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Gradle](https://gradle.org/)

### Importing

- Import and set the project up on your IDE as a gradle project

## Running

### Starting Kafka

Open terminal at kafka home and run the following commands:

 - Starting zookeeper: `./bin/zookeeper-server-start.sh config/zookeeper.properties`
 - Starting kafka-server: `./bin/kafka-server-start.sh config/server.properties`
 - Creating a topic called "importing": `./bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic importing`
 - Starting a producer: `./bin/kafka-console-producer.sh --topic importing --broker-list localhost:9092`
 - Starting a consumer: `./bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic importing --from-beginning`

### Post and validating an object (compromise)
 - On your IDE, run the main class and check the localhost address on http://localhost:8081/
 - Using a client http post test such as [Postman](https://www.getpostman.com/), set up the following config:<br/>
   ```
   URL Request: POST http://localhost:8081/compromise/publish
   Headers: Content-Type: application/json
   Body (raw example): `[{"bank":"001", "payerName": "Bruce Springsteen", "dueDate": "2018-11-02", "amount": 150000.00, "payeeName": "Chad Smith", "paymentDescription": "Last show"},
   {"bank":"001", "payerName": "Joey Ramone", "dueDate": "2018-12-15", "amount": 34000.91, "payeeName": "Iggy Pop", "paymentDescription": "A borrow"}]`
   ```
   
### Rules

 In order to record compromise, many rules are defined for each compromise:
 - Fields bank, amount and dueDate are required;
 - dueDate value must be higher than current date;
 - amount value must be higher than zero.
 
 In case of failure, an exception will be thrown.
