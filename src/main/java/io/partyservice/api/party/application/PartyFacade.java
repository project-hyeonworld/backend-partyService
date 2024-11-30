package io.partyservice.api.party.application;

import io.partyservice.api.party.application.port.in.BeginPartyCommand;
import io.partyservice.api.party.domain.PartyService;
import io.partyservice.api.party.domain.dto.out.PartyInfo;
import io.partyservice.api.party.event.PartyBeginEvent;
import io.partyservice.api.party.event.PartyEventPublisher;
import io.partyservice.api.party.event.PartyTerminateEvent;
import io.partyservice.api.party.event.kafka.PartyKafkaMessageSender;
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
    private final PartyKafkaMessageSender partyKafkaMessageSender;

    @Transactional
    public PartyInfo begin(BeginPartyCommand command) {
        if (command.partyId() != null) {
            partyEventPublisher.execute(PartyTerminateEvent.from(command.partyId()));
        }
        PartyInfo partyInfo = partyService.begin(command.relationType());
        partyKafkaMessageSender.execute(PartyBeginEvent.from(partyInfo.getId()));
        return partyInfo;
    }

    @Transactional
    public long terminate(long partyId) {
        partyEventPublisher.execute(PartyTerminateEvent.from(partyId));
        return partyId;
    }
}
