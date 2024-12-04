package io.partyservice.common.event.kafka.consumer;

import io.partyservice.common.event.CustomEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 12. 4.
 */
public class GenericKafkaConsumerFactory<E extends CustomEvent> implements KafkaConsumerFactory<E> {

    protected final Map<Class<? extends E>, GenericKafkaConsumerStrategy<? extends E, ?, ?>> strategies;

    public GenericKafkaConsumerFactory(List<GenericKafkaConsumerStrategy<? extends E, ?, ?>> kafkaConsumerStrategies) {
        strategies = new HashMap<>();
        for (GenericKafkaConsumerStrategy<? extends E, ?, ?> strategy : kafkaConsumerStrategies) {
            strategies.put(strategy.getEventClass(), strategy);
        }
    }

    public GenericKafkaConsumerStrategy<? extends E, ?, ?> getConsumer(Class<? extends E> eventClass) {
        return strategies.get(eventClass);
    }
}
