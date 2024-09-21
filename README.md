# spring-kafka simple 예제

## simple example
[spring-kafka-simple](spring-kafka-simple)
- KafkaTemplate<String, String>, ObjectMapper 를 활용하여 각 Consumer에서 수동으로 형변환
- 하나의 KafkaListener class 에서 하나의 Topic을 구독하는 예제

## multiple method example
[spring-kafka-multiple](spring-kafka-multiple)

- KafkaTemplate<String, Object>, RecordMessageConverter, ConsumerFactory<String, Object> 설정을 활용하여 각 Consumer별 자동으로 이벤트 객체 변환
- 하나의 KafkaListener class 에서 다수의 Topic을 구독하는 예제

## 프로젝트 구동전 confluent-kafka 설치, 실행
```shell
docker-compose up -d
```


# Ref
[Apache Kafka Support](https://docs.spring.io/spring-boot/reference/messaging/kafka.html)

[Intro to Apache Kafka with Spring](https://www.baeldung.com/spring-kafka)

[@KafkaListener Annotation](https://docs.spring.io/spring-kafka/reference/kafka/receiving-messages/listener-annotation.html)

[Spring Kafka Dead Letter Queue](https://www.baeldung.com/kafka-spring-dead-letter-queue)

