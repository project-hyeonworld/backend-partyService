package io.partyservice.common.event.kafka.consumer;

import io.partyservice.common.event.CustomEvent;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 12. 4.
 */
public interface KafkaConsumerFactory <E extends CustomEvent>{

    KafkaConsumerStrategy<? extends E, ?, ?> getConsumer(Class<? extends E> eventClass);
}
