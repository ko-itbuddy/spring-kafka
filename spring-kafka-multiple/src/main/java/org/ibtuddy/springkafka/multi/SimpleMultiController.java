package org.ibtuddy.springkafka.multi;

import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<String> create(@PathVariable("orderId") int orderId, @PathVariable("itemId") int itemId) {
        KafkaOrderCreatedPayload payload = new KafkaOrderCreatedPayload(orderId, itemId);
        kafkaMultiProducer.publish(payload);
        return ResponseEntity.ok("ok");
    }

    @GetMapping("/cancel/{orderId}")
    public ResponseEntity<String> cancel(@PathVariable("orderId") int orderId) {

        KafkaOrderCancelPayload payload = new KafkaOrderCancelPayload(orderId);
        kafkaMultiProducer.publish(payload);
        return ResponseEntity.ok("ok");
    }

}
