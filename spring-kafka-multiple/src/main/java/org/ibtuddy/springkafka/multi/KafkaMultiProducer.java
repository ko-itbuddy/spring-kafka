package org.ibtuddy.springkafka.multi;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ibtuddy.springkafka.KafkaTopic;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaMultiProducer {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public <T> void publish(KafkaTopic topic, String key, T body){
        kafkaTemplate.send(topic.getTopic(), key, body);
        log.info("kafka message published. topic: {}, key: {}, body: {}", topic, key, body);
    }


}
