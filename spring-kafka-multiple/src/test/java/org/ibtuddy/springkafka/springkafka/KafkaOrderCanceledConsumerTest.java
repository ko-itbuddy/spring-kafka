package org.ibtuddy.springkafka.springkafka;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;

import org.ibtuddy.springkafka.KafkaTopicName;
import org.ibtuddy.springkafka.multi.KafkaMultiProducer;
import org.ibtuddy.springkafka.multi.KafkaOrderCanceledConsumer;
import org.ibtuddy.springkafka.payload.KafkaOrderCancelPayload;
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
        KafkaTopicName.ORDER_CANCELED})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName(" Kafka test : ** 대기 시간이 존재하여 오래 걸림")
public class KafkaOrderCanceledConsumerTest {

    @Autowired
    KafkaTemplate<String, Object> template;

    @Autowired
    KafkaMultiProducer producer;

    @SpyBean
    KafkaOrderCanceledConsumer consumer;

    @Captor
    ArgumentCaptor<KafkaOrderCancelPayload> kafkaOrderCanceledConsumerArgumentCaptor;

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
    @DisplayName("KafkaTopic.ORDER_CANCELED 테스트")
    class KafkaTopic_Name_ORDER_CREATED {

        @Test
        @DisplayName("정상 처리")
        public void success()
            throws Exception {
            int orderId = 1;

            KafkaOrderCancelPayload senPayload = new KafkaOrderCancelPayload(orderId);

            producer.publish(senPayload);

            verify(consumer, timeout(3000).times(1))
                .consumer(kafkaOrderCanceledConsumerArgumentCaptor.capture());

            KafkaOrderCancelPayload kafkaOrderCancelPayload = kafkaOrderCanceledConsumerArgumentCaptor.getValue();

            assertEquals(kafkaOrderCancelPayload.orderId(), 1);

        }

    }


}
