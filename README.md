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
 
