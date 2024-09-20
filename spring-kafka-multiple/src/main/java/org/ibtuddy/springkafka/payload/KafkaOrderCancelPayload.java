package org.ibtuddy.springkafka.payload;

public record KafkaOrderCancelPayload(
    int orderId
) {

}
