package org.ibtuddy.springkafka.springkafka;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;

import org.ibtuddy.springkafka.KafkaTopic;
import org.ibtuddy.springkafka.KafkaTopic.KafkaTopicName;
import org.ibtuddy.springkafka.multi.KafkaMultiProducer;
import org.ibtuddy.springkafka.multi.KafkaOrderCreatedConsumer;
import org.ibtuddy.springkafka.payload.KafkaOrderCancelPayload;
import org.ibtuddy.springkafka.payload.KafkaOrderCreatedPayload;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;


@SpringBootTest
@EmbeddedKafka(partitions = 5,
    topics = {
        KafkaTopicName.ORDER_CREATED,
        KafkaTopicName.ORDER_CANCELED})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName(" Kafka test : ** 대기 시간이 존재하여 오래 걸림")
public class KafkaOrderCreatedConsumerTest {

    @Autowired
    KafkaTemplate<String, Object> template;

    @Autowired
    KafkaMultiProducer producer;

    @SpyBean
    KafkaOrderCreatedConsumer consumer;

    @Captor
    ArgumentCaptor<KafkaOrderCreatedPayload> kafkaOrderCreatedPayloadArgumentCaptor;

    @Captor
    ArgumentCaptor<Object> unknownCaptor;

    // Topic, partition, offset captor 할때 사용
//    @Captor
//    ArgumentCaptor<String> topicArgumentCaptor;
//
//    @Captor
//    ArgumentCaptor<Integer> partitionArgumentCaptor;
//
//    @Captor
//    ArgumentCaptor<Long> offsetArgumentCaptor;


    @Nested
    @DisplayName("KafkaTopic.ORDER_CREATED 테스트")
    class KafkaTopic_ORDER_CREATED {

        @Test
        @DisplayName("정상 처리")
        public void success()
            throws Exception {
            int orderId = 1;
            int itemId = 2;

            producer.publish(KafkaTopic.ORDER_CREATED, String.valueOf(orderId),
                new KafkaOrderCreatedPayload(orderId, itemId));

            verify(consumer, timeout(3000).times(1))
                .consumer(kafkaOrderCreatedPayloadArgumentCaptor.capture());

            KafkaOrderCreatedPayload kafkaOrderCreatedPayload = kafkaOrderCreatedPayloadArgumentCaptor.getValue();

            assertEquals(kafkaOrderCreatedPayload.orderId(), 1);
            assertEquals(kafkaOrderCreatedPayload.itemId(), itemId);

        }

        @Test
        @DisplayName("KafkaTopic.ORDER_CREATED 토픽에 KafkaOrderCancelPayload 를 전달")
        public void withNotMatchedPayload()
            throws Exception {
            int orderId = 1;

            producer.publish(KafkaTopic.ORDER_CREATED, String.valueOf(orderId),
                new KafkaOrderCancelPayload(orderId));

            verify(consumer, timeout(3000).times(1))
                .unknown(unknownCaptor.capture());

            assertEquals(unknownCaptor.getValue().getClass(), KafkaOrderCancelPayload.class);

        }
    }


}
