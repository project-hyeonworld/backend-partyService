package io.partyservice.api.party.event.kafka.consumer;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import io.partyservice.api.party.event.PartyTerminateEvent;
import io.partyservice.common.annotation.Strategy;
import io.partyservice.common.event.kafka.receiver.KafkaConsumerStrategy;
import io.partyservice.common.mapper.ObjectDeserializer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 12. 2.
 */
@Strategy(3)
public class PartyTerminateKafkaConsumerStrategy implements
        KafkaConsumerStrategy<PartyTerminateEvent, String, PartyTerminateEvent> {

    private Duration timeout;
    private KafkaConsumer<String, PartyTerminateEvent> kafkaConsumer;

    public PartyTerminateKafkaConsumerStrategy(@Value("${spring.kafka.broker.url}")String brokerUrl, @Value("${spring.kafka.topic.party.terminate}")String partyTerminateTopic, @Value("${spring.application.name}") String groupId) {
        timeout = Duration.ofMillis(10000);
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, brokerUrl);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ObjectDeserializer.class.getName());
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        kafkaConsumer = new KafkaConsumer<>(props);
        kafkaConsumer.subscribe(Collections.singletonList(partyTerminateTopic));
    }

    @Override
    public Class<PartyTerminateEvent> getEventClass() {
        return PartyTerminateEvent.class;
    }

    @Override
    public KafkaConsumer<String, PartyTerminateEvent> getConsumner() {
        return kafkaConsumer;
    }

    @Override
    public Duration getTimeout() {
        return timeout;
    }

    @Override
    public List<PartyTerminateEvent> receive() {
        List<PartyTerminateEvent> events = new ArrayList<>();
        ConsumerRecords<String, PartyTerminateEvent> records = consume();
        for (ConsumerRecord<String, PartyTerminateEvent> record : records) {
            events.add(record.value());
        }
        return events;
    }

    @Override
    public void close() {

    }
}
