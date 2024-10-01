package org.ibtuddy.springkafka;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class SimpleKafkaController {

    private final KafkaProducer kafkaProducer;

    @GetMapping("/{orderId}/{itemId}")
    public ResponseEntity<String> kafka(@PathVariable int orderId, @PathVariable int itemId) {
        kafkaProducer.publish(KafkaTopic.ORDER_CREATED, String.valueOf(orderId),
                new KafkaOrderPayload(orderId, itemId));
        return ResponseEntity.ok("ok");
    }

}
