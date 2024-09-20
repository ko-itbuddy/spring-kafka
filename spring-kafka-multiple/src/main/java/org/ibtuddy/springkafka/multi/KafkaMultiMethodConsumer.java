package org.ibtuddy.springkafka.multi;

import lombok.extern.slf4j.Slf4j;
import org.ibtuddy.springkafka.payload.KafkaOrderCancelPayload;
import org.ibtuddy.springkafka.payload.KafkaOrderCreatedPayload;
import org.ibtuddy.springkafka.KafkaTopic.KafkaTopicName;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@KafkaListener(id = "multiGroup", topics = { KafkaTopicName.ORDER_CANCELED, KafkaTopicName.ORDER_CREATED })
public class KafkaMultiMethodConsumer {


    @KafkaHandler
    public void kafkaOrderCreatedPayload(KafkaOrderCreatedPayload kafkaOrderCreatedPayload) {
        log.info("multiGroup Received: " + kafkaOrderCreatedPayload);
    }

    @KafkaHandler
    public void kafkaOrderCancelPayload(KafkaOrderCancelPayload kafkaOrderCancelPayload) {
        log.info("multiGroup Received: " + kafkaOrderCancelPayload);
    }

    @KafkaHandler(isDefault = true)
    public void unknown(Object object) {
        log.info("multiGroup Received unknown: " + object);
    }


}
