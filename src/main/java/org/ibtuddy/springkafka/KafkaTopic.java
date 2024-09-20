package org.ibtuddy.springkafka;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public enum KafkaTopic {

    ORDER_CREATED(KafkaTopicName.ORDER_CREATED);

    String topic;


    public static class KafkaTopicName {
        public static final String ORDER_CREATED = "order-created.v1";
    }
}
