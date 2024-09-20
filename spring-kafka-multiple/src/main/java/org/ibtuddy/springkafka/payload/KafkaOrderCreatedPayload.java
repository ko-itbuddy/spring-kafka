package org.ibtuddy.springkafka.payload;

public record KafkaOrderCreatedPayload(
    int orderId,
    int itemId
) {

}
