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

    @GetMapping("/{key}/{body}")
    public ResponseEntity<String> kafka(@PathVariable String key, @PathVariable String body) {
        kafkaProducer.publish(KafkaTopic.ORDER_CREATED, key, body);
        return ResponseEntity.ok("ok");
    }

}
