# Compromises validator

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
   URL Request: POST http://localhost:8081/compromise/publish<br/>
   Headers: Content-Type: application/json<br/>
   Body (raw example): `{"bank":"001", "payerName": "Bruce Springsteen", "dueDate": "2018-11-02", "amount": 150000.00, "payeeName": "Chad Smith", "paymentDescription": "Last show"}`
   ```
