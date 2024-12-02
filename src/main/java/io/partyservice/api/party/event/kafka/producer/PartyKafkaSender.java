package io.partyservice.api.party.event.kafka.producer;

import io.partyservice.api.party.event.PartyEvent;
import io.partyservice.common.event.kafka.producer.KafkaSender;
import io.partyservice.common.event.kafka.producer.KafkaProducerStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 12. 1.
 */
@Component
@RequiredArgsConstructor
public class PartyKafkaSender implements KafkaSender<PartyEvent> {

    private final PartyKafkaProducerManager partyKafkaProducerManager;

    @Override
    public void execute(PartyEvent event) {
        KafkaProducerStrategy kafkaProducer = partyKafkaProducerManager.getProducer(event);
        kafkaProducer.send(event);
        //kafkaProducer.close();
    }
}
