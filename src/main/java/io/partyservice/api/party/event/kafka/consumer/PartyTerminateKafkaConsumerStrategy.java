package io.partyservice.api.party.event.kafka.consumer;

import io.partyservice.common.event.kafka.receiver.DefaultKafkaConsumerStrategy;
import io.partyservice.common.mapper.PartyTerminateEventDeserializer;
import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import io.partyservice.api.party.event.PartyTerminateEvent;
import io.partyservice.common.annotation.Strategy;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 12. 2.
 */
@Strategy(3)
public class PartyTerminateKafkaConsumerStrategy extends
        DefaultKafkaConsumerStrategy<PartyTerminateEvent, String, PartyTerminateEvent> {

    public PartyTerminateKafkaConsumerStrategy(@Value("${spring.kafka.broker.url}")String brokerUrl, @Value("${spring.kafka.topic.party.terminate}")String partyTerminateTopic, @Value("${spring.application.name}") String groupId) {
        timeout = Duration.ofMillis(10000);
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, brokerUrl);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, PartyTerminateEventDeserializer.class.getName());
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        kafkaConsumer = new KafkaConsumer<>(props);
        kafkaConsumer.subscribe(Collections.singletonList(partyTerminateTopic));
    }

    @Override
    public Class<PartyTerminateEvent> getEventClass() {
        return PartyTerminateEvent.class;
    }

    @Override
    protected PartyTerminateEvent convertToEvent(PartyTerminateEvent value) {
        return value;
    }
}
