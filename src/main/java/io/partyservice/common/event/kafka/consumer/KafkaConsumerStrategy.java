package io.partyservice.common.event.kafka.consumer;

import io.partyservice.common.event.CustomEvent;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 12. 2.
 */
public interface KafkaConsumerStrategy<E extends CustomEvent, K, V> {
    Class<E> getEventClass();
}
