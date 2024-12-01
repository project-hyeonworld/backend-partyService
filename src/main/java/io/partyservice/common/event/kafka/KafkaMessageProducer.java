package io.partyservice.common.event.kafka;

import io.partyservice.common.event.CustomEvent;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.Serializer;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 12. 1.
 */
public interface KafkaMessageProducer<E extends CustomEvent, K, V> {

    void initKafkaProducer();

    KafkaProducer<Serializer<K>, Serializer<V>> getProducer(E event);
    ProducerRecord<K, V> produce(E event);
}
