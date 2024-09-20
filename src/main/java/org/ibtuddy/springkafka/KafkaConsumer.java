package org.ibtuddy.springkafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.ibtuddy.springkafka.KafkaTopic.KafkaTopicName;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaConsumer {

    private final ObjectMapper objectMapper = new ObjectMapper().registerModule(
        new JavaTimeModule());

    @KafkaListener(topics = KafkaTopicName.ORDER_CREATED)
    public void orderCreatedConsumer(
        @Header(KafkaHeaders.RECEIVED_KEY) String key,
        @Payload String body,
        @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
        @Header(KafkaHeaders.RECEIVED_PARTITION) int partition) throws JsonProcessingException {
        log.info("kafka message received. topic: {}, key: {}, body: {},  partition: {}", topic, key, body, partition);
        KafkaOrderPayload kafkaOrderPayload = objectMapper.readValue(body, KafkaOrderPayload.class);

        log.info("kafka message deserialized. orderId: {}, itemId: {}", kafkaOrderPayload.orderId(),
            kafkaOrderPayload.itemId());
    }

}
