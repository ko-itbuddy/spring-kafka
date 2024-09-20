package org.ibtuddy.springkafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    public <T> void publish(KafkaTopic topic, String key, T body){
        try {
            String content = objectMapper.writeValueAsString(body);
            kafkaTemplate.send(topic.getTopic(), key, content);
            log.info("kafka message published. topic: {}, key: {}, body: {}", topic, key, content);
        } catch (JsonProcessingException e) {
            log.error("[!] KafkaMessagePublisher.publish() failed. ", e);
        }
    }


}
