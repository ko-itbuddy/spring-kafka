package org.ibtuddy.springkafka.payload;

public interface KafkaBasePayload {
    // get 이 prefix로 지정 되어 있지 않는 경우 key, topic도 Value에 포함되지 않음
    String key();
    String topic();

    // get이 prefix로 지정될 경우 key, topic도 Value에 포함되어 Kafka에 전송됨
//    String getKey();
//    String getTopic();
}
