package org.ibtuddy.springkafka.multi;

import lombok.RequiredArgsConstructor;
import org.ibtuddy.springkafka.KafkaTopic;
import org.ibtuddy.springkafka.payload.KafkaOrderCancelPayload;
import org.ibtuddy.springkafka.payload.KafkaOrderCreatedPayload;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/multi")
@RequiredArgsConstructor
public class SimpleMultiController {

    private final KafkaMultiProducer kafkaMultiProducer;

    @GetMapping("/create/{orderId}/{itemId}")
    public ResponseEntity<String> create(@PathVariable int orderId, @PathVariable int itemId) {
        kafkaMultiProducer.publish(KafkaTopic.ORDER_CREATED, String.valueOf(orderId), new KafkaOrderCreatedPayload(orderId, itemId));
        return ResponseEntity.ok("ok");
    }

    @GetMapping("/cancel/{orderId}")
    public ResponseEntity<String> cancel(@PathVariable int orderId) {
        kafkaMultiProducer.publish(KafkaTopic.ORDER_CANCELED, String.valueOf(orderId), new KafkaOrderCancelPayload(orderId));
        return ResponseEntity.ok("ok");
    }

}
