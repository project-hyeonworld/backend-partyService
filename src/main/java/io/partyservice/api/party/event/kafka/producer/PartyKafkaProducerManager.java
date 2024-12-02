package io.partyservice.api.party.event.kafka.producer;

import io.partyservice.api.party.event.PartyEvent;
import io.partyservice.common.event.kafka.producer.CustomKafkaProducerFactory;
import io.partyservice.common.event.kafka.producer.KafkaProducerManager;
import io.partyservice.common.event.kafka.producer.KafkaProducerStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 12. 1.
 */
@Component
@RequiredArgsConstructor
public class PartyKafkaProducerManager implements KafkaProducerManager<PartyEvent> {

    private final CustomKafkaProducerFactory customKafkaProducerFactory;

    @Override
    public KafkaProducerStrategy getProducer(PartyEvent event) {
        return customKafkaProducerFactory.getStrategy(event);
    }
}
