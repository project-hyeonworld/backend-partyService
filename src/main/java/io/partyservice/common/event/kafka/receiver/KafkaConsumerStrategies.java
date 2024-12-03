package io.partyservice.common.event.kafka.receiver;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 12. 2.
 */
@Component
public class KafkaConsumerStrategies {
    private final List<DefaultKafkaConsumerStrategy> consumers;

    @Autowired
    public KafkaConsumerStrategies(List<DefaultKafkaConsumerStrategy> kafkaConsumers) {
        consumers = new ArrayList<>(kafkaConsumers);
    }

    public static KafkaConsumerStrategies from(List<DefaultKafkaConsumerStrategy> collect) {
        return new KafkaConsumerStrategies(collect);
    }

    public void add(DefaultKafkaConsumerStrategy strategy) {
        consumers.add(strategy);
    }

    public int size() {
        return consumers.size();
    }
}
