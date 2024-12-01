package io.partyservice.api.party.event.kafka;

import io.partyservice.api.party.event.PartyBeginEvent;
import io.partyservice.api.party.event.PartyEvent;
import io.partyservice.common.event.kafka.KafkaMessageProducer;
import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 12. 1.
 */
@Component
public class PartyKafkaMessageProducer implements KafkaMessageProducer<PartyEvent, String, Long> {

    private final String BROKER_URL;
    private final String PARTY_TOPIC_BEGIN;
    private KafkaProducer<String, Long> kafkaProducer;

    public PartyKafkaMessageProducer(@Value("${spring.kafka.broker.url}") String brokerUrl,
            @Value("${spring.kafka.topic.party.begin}") String partyTopicBegin) {
        BROKER_URL = brokerUrl;
        PARTY_TOPIC_BEGIN = partyTopicBegin;
        initKafkaProducer();
    }

    @Override
    public void initKafkaProducer() {
        Properties props = new Properties();
        props.put("bootstrap.servers", BROKER_URL); // Kafka 서버 주소
        props.put("key.serializer", StringSerializer.class.getName());
        props.put("value.serializer", LongSerializer.class.getName());
        this.kafkaProducer = new KafkaProducer<>(props);
    }

    @Override
    public KafkaProducer getProducer(PartyEvent event) {
        return kafkaProducer;
    }

    @Override
    public ProducerRecord produce(PartyEvent event) {
        if (event instanceof PartyBeginEvent) {
            return produceByEvent((PartyBeginEvent) event);
        }
        return null;
    }

    private ProducerRecord produceByEvent(PartyBeginEvent event) {
        return new ProducerRecord<>(PARTY_TOPIC_BEGIN, event.partyId());
    }
}
