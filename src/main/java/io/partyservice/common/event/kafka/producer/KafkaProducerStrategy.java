package io.partyservice.common.event.kafka.producer;

import io.partyservice.common.event.CustomEvent;
import org.apache.kafka.clients.producer.ProducerRecord;;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 12. 2.
 */
public interface KafkaProducerStrategy<E extends CustomEvent, K, V> {

    Class<E> getEventClass();

    default String getEventName() {
        return getEventClass().getName();
    }

    void send(E event);

    ProducerRecord<K, V> produce(E event);

    void close();
}
