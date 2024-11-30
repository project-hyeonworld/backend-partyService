package io.partyservice.api.party.interfaces;

import static org.springframework.transaction.event.TransactionPhase.AFTER_COMMIT;
import static org.springframework.transaction.event.TransactionPhase.AFTER_COMPLETION;

import io.partyservice.api.party.domain.PartyService;
import io.partyservice.api.party.event.PartyEvent;
import io.partyservice.api.party.event.PartyBeginEvent;
import io.partyservice.api.party.event.PartyTerminateEvent;
import lombok.RequiredArgsConstructor;
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
    //private final PartyDashboardService partyDashboardService;

    @Override
    @Async
    @TransactionalEventListener(phase = AFTER_COMMIT)
    public void handleEvent(PartyEvent event) {
        if (event instanceof PartyBeginEvent) {
            handlePartyBeginEvent((PartyBeginEvent) event);
            return;
        }
        if (event instanceof PartyTerminateEvent) {
            handlePartyTerminateEvent((PartyTerminateEvent) event);
            return;
        }
        throw new IllegalArgumentException("Unexpected event type: " + event.getClass());
    }

    @Override
    public void handlePartyTerminateEvent(PartyTerminateEvent event) {
        partyService.terminate(event.partyId());
    }

    @Override
    public void handlePartyBeginEvent(PartyBeginEvent event) {
        return;
        //partyDashboardService.createPartyEntityDashboard(event.partyId());
    }
}
