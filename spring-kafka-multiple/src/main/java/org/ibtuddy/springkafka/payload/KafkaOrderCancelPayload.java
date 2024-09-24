package org.ibtuddy.springkafka.payload;

import org.ibtuddy.springkafka.KafkaTopicName;

public record KafkaOrderCancelPayload(
    int orderId
) implements KafkaBasePayload {

    @Override
    public String key() {
        return String.valueOf(this.orderId);
    }

    @Override
    public String topic() {
        return KafkaTopicName.ORDER_CANCELED;
    }
}
