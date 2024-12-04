package io.partyservice.api.party.event.kafka;

import io.partyservice.api.party.event.PartyEvent;
import java.time.LocalDateTime;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 12. 2.
 */
public record PartyTerminateKafkaEvent(
        long partyId,
        LocalDateTime terminatedAt
) implements PartyEvent {

    public static PartyTerminateKafkaEvent from(Long partyId, LocalDateTime terminatedAt) {
        return new PartyTerminateKafkaEvent(partyId, terminatedAt);
    }
}