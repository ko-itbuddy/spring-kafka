package org.ibtuddy.springkafka;

public record KafkaOrderPayload(
    int orderId,
    int itemId
) {

}
