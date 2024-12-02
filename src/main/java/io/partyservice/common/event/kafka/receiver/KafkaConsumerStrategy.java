package io.partyservice.common.event.kafka.receiver;

import io.partyservice.common.event.CustomEvent;
import java.time.Duration;
import java.util.List;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 12. 2.
 */
public interface KafkaConsumerStrategy<E extends CustomEvent, K, V> {
    Class<E> getEventClass();
    KafkaConsumer<K, V> getConsumner();
    Duration getTimeout();

    default String getEventName() {
        return getEventClass().getName();
    }
    List<E> receive();
    default ConsumerRecords<K, V> consume() {
        return getConsumner().poll(getTimeout());
    }

    void close();
}
