package io.partyservice.common.event.kafka.receiver;

import io.partyservice.common.event.CustomEvent;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 12. 2.
 */
public interface KafkaConsumerManager<E extends CustomEvent> {

    DefaultKafkaConsumerStrategy getConsumer(Class<E> eventClass);
}
