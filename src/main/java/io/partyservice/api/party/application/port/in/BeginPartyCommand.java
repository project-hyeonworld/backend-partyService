package io.partyservice.api.party.application.port.in;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 5.
 */
public record BeginPartyCommand(
        Long partyId,
    int relationType
){

}
