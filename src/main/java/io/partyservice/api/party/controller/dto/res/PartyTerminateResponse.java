package io.partyservice.api.party.controller.dto.res;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 11. 4.
 */
public record PartyTerminateResponse(
        long id
) implements PartyResponse {

    public static PartyTerminateResponse from(long partyId) {
        return new PartyTerminateResponse(partyId);
    }
}
