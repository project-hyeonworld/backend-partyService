package io.partyservice.api.party.event.kafka;

import io.partyservice.api.party.event.PartyEvent;
import io.partyservice.common.event.kafka.KafkaMessageSender;
import io.partyservice.common.event.kafka.KafkaProducerStrategy;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.stereotype.Component;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 12. 1.
 */
@Component
@RequiredArgsConstructor
public class PartyKafkaMessageSender implements KafkaMessageSender<PartyEvent> {

    private final PartyKafkaMessageProducer partyKafkaMessageProducer;

    @Override
    public void execute(PartyEvent event) {
        KafkaProducerStrategy kafkaProducer = partyKafkaMessageProducer.getProducer(event);
        kafkaProducer.send(event);
        //kafkaProducer.close();
    }
}
