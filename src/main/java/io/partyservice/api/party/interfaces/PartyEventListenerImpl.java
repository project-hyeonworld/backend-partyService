package io.partyservice.api.party.interfaces;

import static org.springframework.transaction.event.TransactionPhase.AFTER_COMMIT;

import io.partyservice.api.party.domain.PartyService;
import io.partyservice.api.party.event.PartyEvent;
import io.partyservice.api.party.event.PartyEvent.Begin;
import io.partyservice.api.party.event.PartyEvent.Terminate;
import lombok.RequiredArgsConstructor;
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
    @TransactionalEventListener(phase = AFTER_COMMIT)
    public void handleEvent(PartyEvent event) {
        if (event instanceof Begin) {
            handlePartyEntityBeginEvent((Begin) event);
            return;
        }
        if (event instanceof Terminate) {
            handlePartyEntityTerminateEvent((Terminate) event);
            return;
        }
        throw new IllegalArgumentException("Unexpected event type: " + event.getClass());
    }

    @Override
    public void handlePartyEntityTerminateEvent(Terminate event) {
        partyService.terminate(event.partyId());
    }

    @Override
    public void handlePartyEntityBeginEvent(Begin event) {
        return;
        //partyDashboardService.createPartyEntityDashboard(event.partyId());
    }
}
