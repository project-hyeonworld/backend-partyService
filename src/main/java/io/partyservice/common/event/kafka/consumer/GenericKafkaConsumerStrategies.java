package io.partyservice.common.event.kafka.consumer;

import io.partyservice.common.event.CustomEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 12. 2.
 */
public class GenericKafkaConsumerStrategies<E extends CustomEvent, K, V> {
    private final List<GenericKafkaConsumerStrategy<? extends E, K, V>> consumers;

    public GenericKafkaConsumerStrategies(List<GenericKafkaConsumerStrategy<? extends E, K, V>> kafkaConsumers) {
        consumers = new ArrayList<>(kafkaConsumers);
    }

    public static GenericKafkaConsumerStrategies from(List<GenericKafkaConsumerStrategy> collect) {
        return new GenericKafkaConsumerStrategies(collect);
    }

    public void add(GenericKafkaConsumerStrategy<? extends E, K, V> strategy) {
        consumers.add(strategy);
    }

    public int size() {
        return consumers.size();
    }
}
