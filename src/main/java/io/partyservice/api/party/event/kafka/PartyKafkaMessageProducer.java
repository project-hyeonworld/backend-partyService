package io.partyservice.api.party.event.kafka;

import io.partyservice.api.party.event.PartyEvent;
import io.partyservice.common.event.kafka.CustomKafkaProducerFactory;
import io.partyservice.common.event.kafka.KafkaMessageProducer;
import io.partyservice.common.event.kafka.KafkaProducerStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 12. 1.
 */
@Component
@RequiredArgsConstructor
public class PartyKafkaMessageProducer implements KafkaMessageProducer<PartyEvent, String, Long> {

    private final CustomKafkaProducerFactory customKafkaProducerFactory;

    @Override
    public KafkaProducerStrategy getProducer(PartyEvent event) {
        return customKafkaProducerFactory.getStrategy(event);
    }
}
