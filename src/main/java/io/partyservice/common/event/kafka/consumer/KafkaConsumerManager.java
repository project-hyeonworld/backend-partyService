package io.partyservice.common.event.kafka.consumer;

import io.partyservice.common.event.CustomEvent;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 12. 1.
 */
public interface KafkaConsumerManager<E extends CustomEvent> {

    KafkaConsumerStrategy<? extends E, ?, ?> getConsumer(Class<? extends E> eventClass);
}