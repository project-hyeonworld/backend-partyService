package io.partyservice.api.party.event.kafka.consumer;

import io.partyservice.api.party.event.kafka.PartyTerminateKafkaEvent;
import io.partyservice.common.event.kafka.consumer.GenericKafkaConsumerFactory;
import io.partyservice.common.event.kafka.consumer.GenericKafkaConsumerManager;
import org.springframework.stereotype.Component;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 12. 2.
 */
@Component
public class PartyKafkaConsumerManager extends GenericKafkaConsumerManager<PartyTerminateKafkaEvent> {

    public PartyKafkaConsumerManager(
            GenericKafkaConsumerFactory<PartyTerminateKafkaEvent> genericKafkaConsumerFactory) {
        super(genericKafkaConsumerFactory);
    }
}
