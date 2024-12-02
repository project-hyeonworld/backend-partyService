package io.partyservice.common.event.kafka.receiver;

import io.partyservice.common.event.EventListener;
import java.util.List;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 12. 2.
 */
public interface KafkaReceiver <E> extends EventListener<E> {
    void execute();
    default void handleEvents(List<E> events) {
        for (E event : events) {
            handleEvent(event);
        }
    }
}
