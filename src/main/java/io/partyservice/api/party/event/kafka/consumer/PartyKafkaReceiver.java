package io.partyservice.api.party.event.kafka.consumer;

import io.partyservice.api.party.event.PartyEvent;
import io.partyservice.api.party.event.PartyEventPublisher;
import io.partyservice.api.party.event.PartyTerminateEvent;
import io.partyservice.api.party.event.PartyTerminateKafkaEvent;
import io.partyservice.common.event.kafka.receiver.KafkaConsumerStrategy;
import io.partyservice.common.event.kafka.receiver.KafkaReceiver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 12. 2.
 */
@Component
@RequiredArgsConstructor
public class PartyKafkaReceiver implements KafkaReceiver<PartyEvent> {

    private final PartyKafkaConsumerManager partyKafkaConsumerManager;
    private final PartyEventPublisher eventPublisher;


    @Override
    public void execute() {
        KafkaConsumerStrategy consumer = partyKafkaConsumerManager.getConsumer(PartyTerminateEvent.class);
        while (true) {
            handleEvents(consumer.receive());
        }
    }

    @Override
    public void handleEvent(PartyEvent event) {
        if (event instanceof PartyTerminateEvent) {
            eventPublisher.execute(PartyTerminateKafkaEvent.from(((PartyTerminateEvent) event).partyId()));
            return;
        }
    }
}
