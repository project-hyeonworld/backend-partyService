package io.partyservice.common.event.kafka.receiver;

import io.partyservice.api.party.event.PartyTerminateEvent;
import io.partyservice.api.party.event.kafka.consumer.PartyTerminateKafkaConsumerStrategy;
import io.partyservice.common.event.CustomEvent;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 12. 2.
 */
public interface KafkaConsumerManager<E extends CustomEvent> {

    KafkaConsumerStrategy getConsumer(Class<E> eventClass);
}
