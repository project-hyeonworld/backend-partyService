package io.partyservice.api.party.event.kafka.consumer;

import io.partyservice.api.party.event.PartyEvent;

import io.partyservice.common.event.kafka.receiver.CustomKafkaConsumerFactory;
import io.partyservice.common.event.kafka.receiver.KafkaConsumerManager;

import io.partyservice.common.event.kafka.receiver.KafkaConsumerStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 12. 2.
 */
@Component
@RequiredArgsConstructor
public class PartyKafkaConsumerManager implements KafkaConsumerManager<PartyEvent> {

    private final CustomKafkaConsumerFactory customKafkaConsumerFactory;

    @Override
    public KafkaConsumerStrategy getConsumer(Class eventClass) {
        return customKafkaConsumerFactory.getConsumer(eventClass);
    }
}
