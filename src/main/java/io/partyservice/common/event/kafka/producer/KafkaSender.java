package io.partyservice.common.event.kafka.producer;

import io.partyservice.common.event.EventPublisher;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 12. 1.
 */
public interface KafkaSender<E> extends EventPublisher<E> {
}
