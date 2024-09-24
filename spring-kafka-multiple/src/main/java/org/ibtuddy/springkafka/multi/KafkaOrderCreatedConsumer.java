package org.ibtuddy.springkafka.multi;

import lombok.extern.slf4j.Slf4j;
import org.ibtuddy.springkafka.KafkaTopic.KafkaTopicName;
import org.ibtuddy.springkafka.payload.KafkaOrderCancelPayload;
import org.ibtuddy.springkafka.payload.KafkaOrderCreatedPayload;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@KafkaListener(
    id = "orderCreated",
    topics = {KafkaTopicName.ORDER_CREATED}
)
public class KafkaOrderCreatedConsumer {


    @KafkaHandler
    public void consumer(KafkaOrderCreatedPayload kafkaOrderCreatedPayload) {
        log.info("multiGroup Received: " + kafkaOrderCreatedPayload);
    }

    @KafkaHandler(isDefault = true)
    public void unknown(Object object) {
        log.error("multiGroup Received unknown: " + object);
    }


}
