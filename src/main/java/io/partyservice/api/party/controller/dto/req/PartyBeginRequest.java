package io.partyservice.api.party.controller.dto.req;

import io.partyservice.api.party.application.port.in.BeginPartyCommand;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 11. 4.
 */
public record PartyBeginRequest(
        Long partyId,
        int relationType
) implements PartyRequest {

    public BeginPartyCommand toCommand() {
        return new BeginPartyCommand(partyId, relationType);
    }
}
