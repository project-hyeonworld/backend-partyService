package io.partyservice.common.event.kafka.producer;

import io.partyservice.common.event.CustomEvent;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 12. 1.
 */
public interface KafkaProducerManager<E extends CustomEvent> {
    KafkaProducerStrategy getProducer(E event);
}
