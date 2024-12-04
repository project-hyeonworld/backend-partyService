package io.partyservice.api.party.event.kafka.consumer;

import io.partyservice.api.party.event.PartyEventPublisher;
import io.partyservice.api.party.event.kafka.PartyTerminateKafkaEvent;
import io.partyservice.common.event.kafka.consumer.GenericKafkaConsumerStrategy;
import io.partyservice.common.event.kafka.consumer.KafkaReceiver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 12. 2.
 */
@Component
@RequiredArgsConstructor
public class PartyTerminateKafkaReceiver implements KafkaReceiver<PartyTerminateKafkaEvent> {

    private final PartyKafkaConsumerManager manager;
    private final PartyEventPublisher eventPublisher;


    @Override
    public void execute() {
        GenericKafkaConsumerStrategy consumer = manager.getConsumer(PartyTerminateKafkaEvent.class);
        while (true) {
            handleEvents(consumer.receive());
        }
    }

    @Override
    public void handleEvent(PartyTerminateKafkaEvent event) {
        eventPublisher.execute(event);
    }
}
