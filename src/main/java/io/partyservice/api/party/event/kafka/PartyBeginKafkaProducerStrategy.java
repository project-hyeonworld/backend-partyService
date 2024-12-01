package io.partyservice.api.party.event.kafka;

import io.partyservice.api.party.event.PartyBeginEvent;
import io.partyservice.common.annotation.Strategy;
import io.partyservice.common.event.kafka.KafkaProducerStrategy;
import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 12. 2.
 */
@Strategy(0)
public class PartyBeginKafkaProducerStrategy implements KafkaProducerStrategy<PartyBeginEvent, String, Long> {

    private final String BROKER_URL;
    private final String PARTY_BEGIN_TOPIC;

    private KafkaProducer<String, Long> kafkaProducer;

    public PartyBeginKafkaProducerStrategy(@Value("${spring.kafka.broker.url}") String brokerUrl, @Value("${spring.kafka.topic.party.begin}") String partyBeginTopic) {
        BROKER_URL = brokerUrl;
        PARTY_BEGIN_TOPIC = partyBeginTopic;
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BROKER_URL); // Kafka 서버 주소
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, LongSerializer.class.getName());
        kafkaProducer = new KafkaProducer<>(props);
    }

    @Override
    public String getEventName() {
        return PartyBeginEvent.class.getName();
    }

    @Override
    public void send(PartyBeginEvent event) {
        kafkaProducer.send(produce(event));
    }

    @Override
    public ProducerRecord<String, Long> produce(PartyBeginEvent event) {
        return new ProducerRecord<>(PARTY_BEGIN_TOPIC, event.partyId());
    }

    @Override
    public void close() {
        kafkaProducer.close();
    }
}
