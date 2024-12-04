package io.partyservice.common.event.kafka.consumer;

import io.partyservice.common.event.CustomEvent;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 12. 4.
 */
public class GenericKafkaConsumerManager<E extends CustomEvent> implements KafkaConsumerManager<E> {

    protected GenericKafkaConsumerFactory<E> factory;

    public GenericKafkaConsumerManager(GenericKafkaConsumerFactory<E> genericKafkaConsumerFactory) {
        factory = genericKafkaConsumerFactory;
    }

    public GenericKafkaConsumerStrategy<? extends E, ?, ?> getConsumer(Class<? extends E> eventClass) {
        return factory.getConsumer(eventClass);
    }
}
