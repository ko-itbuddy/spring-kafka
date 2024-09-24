package org.ibtuddy.springkafka.multi;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ibtuddy.springkafka.payload.KafkaBasePayload;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaMultiProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public <T extends KafkaBasePayload> void publish(T payload) {
        kafkaTemplate.send(payload.topic(), payload.key(), payload);
        log.info("kafka message published. topic: {}, key: {}, body: {}", payload.topic(),
            payload.key(), payload);
    }


}
