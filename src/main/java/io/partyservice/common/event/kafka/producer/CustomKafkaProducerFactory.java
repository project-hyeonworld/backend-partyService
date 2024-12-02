package io.partyservice.common.event.kafka.producer;

import io.partyservice.common.annotation.StrategyFactory;
import io.partyservice.common.event.CustomEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 12. 2.
 */
@StrategyFactory
public class CustomKafkaProducerFactory<E extends CustomEvent> {

    private final Map<String, KafkaProducerStrategy> strategies;

    public CustomKafkaProducerFactory(List<KafkaProducerStrategy> kafkaProducerStrategies) {
        this.strategies = new HashMap<>();
        for (KafkaProducerStrategy kafkaMessageProducerStrategy : kafkaProducerStrategies) {
            strategies.put(kafkaMessageProducerStrategy.getEventName(), kafkaMessageProducerStrategy);
        }
    }

    public KafkaProducerStrategy getStrategy(E event) {
        KafkaProducerStrategy kafkaMessageProducerStrategy = strategies.get(event.getClass().getName());
        if (strategies == null) {
            throw new IllegalArgumentException("No strategy found for gameId: ");
        }
        return kafkaMessageProducerStrategy;
    }
}
