package io.partyservice.common.event.kafka.receiver;

import io.partyservice.common.event.CustomEvent;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 12. 2.
 */
@Component
public class KafkaConsumerStrategies<E extends CustomEvent> {
    private final List<KafkaConsumerStrategy> consumers;

    @Autowired
    public KafkaConsumerStrategies(List<KafkaConsumerStrategy> kafkaConsumers) {
        consumers = new ArrayList<>(kafkaConsumers);
    }

    public static KafkaConsumerStrategies from(List<KafkaConsumerStrategy> collect) {
        return new KafkaConsumerStrategies(collect);
    }

    public void add(KafkaConsumerStrategy strategy) {
        consumers.add(strategy);
    }

    public int size() {
        return consumers.size();
    }
}
