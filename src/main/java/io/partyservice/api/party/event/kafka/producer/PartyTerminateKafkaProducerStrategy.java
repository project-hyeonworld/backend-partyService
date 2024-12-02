package io.partyservice.api.party.event.kafka.producer;

import io.partyservice.api.party.event.PartyTerminateEvent;
import io.partyservice.common.annotation.Strategy;
import io.partyservice.common.event.kafka.producer.KafkaProducerStrategy;
import io.partyservice.common.mapper.ObjectSerializer;
import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 12. 2.
 */
@Strategy(1)
public class PartyTerminateKafkaProducerStrategy implements
        KafkaProducerStrategy<PartyTerminateEvent, String, PartyTerminateEvent> {

    private final String PARTY_TERMINATE_TOPIC;
    private final KafkaProducer<String, PartyTerminateEvent> kafkaProducer;

    public PartyTerminateKafkaProducerStrategy(@Value("${spring.kafka.broker.url}") String brokerUrl,
            @Value("${spring.kafka.topic.party.terminate}") String partyTerminateTopic) {
        PARTY_TERMINATE_TOPIC = partyTerminateTopic;
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, brokerUrl);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, LongSerializer.class.getName());
        kafkaProducer = new KafkaProducer<>(props);
    }

    @Override
    public String getTopic() {
        return PARTY_TERMINATE_TOPIC;
    }

    @Override
    public KafkaProducer<String, PartyTerminateEvent> getProducer() {
        return null;
    }

    @Override
    public Class<PartyTerminateEvent> getEventClass() {
        return PartyTerminateEvent.class;
    }

    @Override
    public void send(PartyTerminateEvent event) {
        kafkaProducer.send(produce(event));
    }

    @Override
    public ProducerRecord<String, PartyTerminateEvent> produce(PartyTerminateEvent event) {
        return new ProducerRecord<>(PARTY_TERMINATE_TOPIC, event);
    }

    @Override
    public void close() {
        kafkaProducer.close();
    }
}
