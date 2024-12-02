package io.partyservice.api.party.event;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 12. 2.
 */
public record PartyTerminateKafkaEvent(long partyId) implements PartyEvent {

    public static PartyTerminateKafkaEvent from(Long partyId) {
        return new PartyTerminateKafkaEvent(partyId);
    }
}