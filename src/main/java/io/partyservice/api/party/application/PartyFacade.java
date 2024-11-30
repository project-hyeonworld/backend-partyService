package io.partyservice.api.party.application;

import io.partyservice.api.party.application.port.in.BeginPartyCommand;
import io.partyservice.api.party.domain.PartyService;
import io.partyservice.api.party.domain.dto.out.Party;
import io.partyservice.api.party.event.PartyEvent.Begin;
import io.partyservice.api.party.event.PartyEvent.Terminate;
import io.partyservice.api.party.event.PartyEventPublisher;
import io.partyservice.common.annotation.Facade;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 5.
 */
@Facade
@RequiredArgsConstructor
public class PartyFacade {

    private final PartyService partyService;
    private final PartyEventPublisher partyEventPublisher;


    @Transactional
    public Party begin(BeginPartyCommand command) {
        if (command.partyId() != null) {
            partyEventPublisher.execute(new Terminate(command.partyId()));
        }
        Party party = partyService.begin(command.relationType());
        partyEventPublisher.execute(new Begin(party.getId()));
        return party;
    }

    @Transactional
    public long terminate(long partyId) {
        partyEventPublisher.execute(new Terminate(partyId));
        return partyId;
    }
}
