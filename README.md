# Compromises validator

## Setup

### Requirements

- [Apache Kafka](https://kafka.apache.org/downloads)
- [Java](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Gradle](https://gradle.org/)

### Starting Kafka

Open terminal at kafka home and run the following commands:

 - Starting zookeeper: `./bin/zookeeper-server-start.sh config/zookeeper.properties`
 - Starting kafka-server: `./bin/kafka-server-start.sh config/server.properties`
 - Creating a topic called "importing": `./bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic importing`
 - Starting a producer: `./bin/kafka-console-producer.sh --topic importing --broker-list localhost:9092`
 - Starting a consumer: `./bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic importing --from-beginning`
 
