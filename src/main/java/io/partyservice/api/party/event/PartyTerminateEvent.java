package io.partyservice.api.party.event;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 12. 1.
 */
public record PartyTerminateEvent(long partyId) implements PartyEvent {

    public static PartyTerminateEvent from(Long partyId) {
        return new PartyTerminateEvent(partyId);
    }
}