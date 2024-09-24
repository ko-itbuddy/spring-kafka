package org.ibtuddy.springkafka.multi;

import lombok.extern.slf4j.Slf4j;
import org.ibtuddy.springkafka.KafkaTopicName;
import org.ibtuddy.springkafka.payload.KafkaOrderCancelPayload;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@KafkaListener(
    id = "orderCanceled",
    topics = {KafkaTopicName.ORDER_CANCELED}
)
public class KafkaOrderCanceledConsumer {

    @KafkaHandler
    public void consumer(KafkaOrderCancelPayload kafkaOrderCancelPayload) {
        log.info("multiGroup Received: " + kafkaOrderCancelPayload);
    }

    @KafkaHandler(isDefault = true)
    public void unknown(Object object) {
        log.error("multiGroup Received unknown: " + object);
    }


}
