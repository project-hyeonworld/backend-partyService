package io.partyservice.common.event.kafka.receiver;

import io.partyservice.common.annotation.StrategyFactory;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 12. 2.
 */
@StrategyFactory
public class CustomKafkaConsumerFactory {
    private final Map<String, KafkaConsumerStrategy> strategies;

    public CustomKafkaConsumerFactory(List<KafkaConsumerStrategy> kafkaConsumerStrategies) {
        this.strategies = new HashMap<>();
        for (KafkaConsumerStrategy kafkaConsumerStrategy : kafkaConsumerStrategies) {
            strategies.put(kafkaConsumerStrategy.getEventName(), kafkaConsumerStrategy);
        }
    }

    public KafkaConsumerStrategy getConsumer(Class eventClass) {
        return strategies.get(eventClass.getName());
    }
}


