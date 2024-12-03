package io.partyservice.common.event.kafka.receiver;

import io.partyservice.common.event.CustomEvent;
import java.time.Duration;
import java.util.List;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 12. 2.
 */
public interface KafkaConsumerStrategy<E extends CustomEvent> {
    Class<E> getEventClass();
}
