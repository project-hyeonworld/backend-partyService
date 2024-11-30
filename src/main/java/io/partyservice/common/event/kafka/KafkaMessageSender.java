package io.partyservice.common.event.kafka;

import io.partyservice.common.event.EventPublisher;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 12. 1.
 */
public interface KafkaMessageSender<E> extends EventPublisher<E> {
}
