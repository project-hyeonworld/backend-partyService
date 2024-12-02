 package io.partyservice.api.party.event.kafka.producer;

import io.partyservice.api.party.event.PartyBeginEvent;
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
@Strategy(0)
public class PartyBeginKafkaProducerStrategy implements KafkaProducerStrategy<PartyBeginEvent, String, Long> {

    private final String PARTY_BEGIN_TOPIC;

    private KafkaProducer<String, Long> kafkaProducer;

    public PartyBeginKafkaProducerStrategy(@Value("${spring.kafka.broker.url}") String brokerUrl, @Value("${spring.kafka.topic.party.begin}") String partyBeginTopic) {
        PARTY_BEGIN_TOPIC = partyBeginTopic;
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, brokerUrl);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, LongSerializer.class.getName());
        kafkaProducer = new KafkaProducer<>(props);
    }

    @Override
    public String getTopic() {
        return PARTY_BEGIN_TOPIC;
    }

    @Override
    public KafkaProducer<String, PartyBeginEvent> getProducer() {
        return kafkaProducer;
    }

    @Override
    public Class<PartyBeginEvent> getEventClass() {
        return PartyBeginEvent.class;
    }

    @Override
    public void send(PartyBeginEvent event) {
        kafkaProducer.send(produce(event));
    }

    @Override
    public ProducerRecord<String, PartyBeginEvent> produce(PartyBeginEvent event) {
        return new ProducerRecord<>(PARTY_BEGIN_TOPIC, event);
    }

    @Override
    public void close() {
        kafkaProducer.close();
    }
}
