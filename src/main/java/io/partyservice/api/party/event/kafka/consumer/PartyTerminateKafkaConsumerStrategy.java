package io.partyservice.api.party.event.kafka.consumer;

import io.partyservice.api.party.event.kafka.PartyTerminateKafkaEvent;
import io.partyservice.common.event.kafka.consumer.GenericKafkaConsumerStrategy;
import io.partyservice.common.mapper.PartyTerminateEventDeserializer;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Properties;
import io.partyservice.api.party.event.PartyTerminateEvent;
import io.partyservice.common.annotation.Strategy;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 12. 2.
 */
@Strategy(3)
public class PartyTerminateKafkaConsumerStrategy extends
        GenericKafkaConsumerStrategy<PartyTerminateKafkaEvent, String, PartyTerminateEvent> {

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
    public Class<PartyTerminateKafkaEvent> getEventClass() {
        return PartyTerminateKafkaEvent.class;
    }

    @Override
    protected PartyTerminateKafkaEvent convertToEvent(ConsumerRecord<String, PartyTerminateEvent> record) {
        LocalDateTime terminatedAt = convertTimestampToLocalDateTime(record.timestamp());
        return PartyTerminateKafkaEvent.from(record.value().partyId(), terminatedAt);
    }

    private LocalDateTime convertTimestampToLocalDateTime(long timestamp) {
        Instant instant = Instant.ofEpochMilli(timestamp);
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }
}
