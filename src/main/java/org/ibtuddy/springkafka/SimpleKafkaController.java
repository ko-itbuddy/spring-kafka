package org.ibtuddy.springkafka;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class SimpleKafkaController {

    private final KafkaProducer kafkaProducer;

    @GetMapping("/{orderId}/{itemId}")
    public ResponseEntity<String> kafka(@PathVariable int orderId, @PathVariable int itemId) {
        kafkaProducer.publish(KafkaTopic.ORDER_CREATED, String.valueOf(orderId), new KafkaOrderPayload(orderId, itemId));
        return ResponseEntity.ok("ok");
    }

}
