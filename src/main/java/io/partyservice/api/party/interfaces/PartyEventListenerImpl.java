package io.partyservice.api.party.interfaces;

import static org.springframework.transaction.event.TransactionPhase.AFTER_COMMIT;

import io.partyservice.api.party.domain.PartyService;
import io.partyservice.api.party.event.PartyEvent;
import io.partyservice.api.party.event.PartyBeginEvent;
import io.partyservice.api.party.event.PartyTerminateEvent;
import io.partyservice.api.party.event.kafka.PartyTerminateKafkaEvent;
import io.partyservice.api.party.event.kafka.producer.PartyKafkaSender;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 5.
 */
@Component
@RequiredArgsConstructor
public class PartyEventListenerImpl implements PartyEventListener {

    private final PartyService partyService;
    private final PartyKafkaSender partyKafkaSender;

    @Override
    @Async
    @TransactionalEventListener(phase = AFTER_COMMIT)
    public void handleEvent(PartyEvent event) {
        if (event instanceof PartyBeginEvent) {
            handleEventBy((PartyBeginEvent) event);
            return;
        }

        if (event instanceof PartyTerminateEvent) {
            handleEventBy((PartyTerminateEvent) event);
            return;
        }
        throw new IllegalArgumentException("Unexpected event type: " + event.getClass());
    }

    @EventListener
    public void handleEvent(PartyTerminateKafkaEvent event) {
        handleEventBy(event);
    }


    private void handleEventBy(PartyTerminateEvent event) {
        partyKafkaSender.execute(event);
    }

    private void handleEventBy(PartyTerminateKafkaEvent event) {
        partyService.terminate(event.partyId());
    }


    private void handleEventBy(PartyBeginEvent event) {
        partyKafkaSender.execute(event);
    }
}
