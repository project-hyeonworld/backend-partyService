package io.partyservice.api.party.event.kafka.consumer;

import io.partyservice.api.party.event.kafka.PartyTerminateKafkaEvent;
import io.partyservice.common.event.kafka.consumer.GenericKafkaConsumerFactory;
import io.partyservice.common.event.kafka.consumer.GenericKafkaConsumerStrategy;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 12. 4.
 */
@Component
public class PartyTerminateKafkaCOnsumerFactory extends GenericKafkaConsumerFactory<PartyTerminateKafkaEvent> {

    public PartyTerminateKafkaCOnsumerFactory(
            List<GenericKafkaConsumerStrategy<? extends PartyTerminateKafkaEvent, ?, ?>> genericKafkaConsumerStrategies) {
        super(genericKafkaConsumerStrategies);
    }
}
