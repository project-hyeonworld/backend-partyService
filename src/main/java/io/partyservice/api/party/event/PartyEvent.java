package io.partyservice.api.party.event;

import io.partyservice.common.event.CustomEvent;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 5.
 */
public sealed interface PartyEvent extends CustomEvent {

    long partyId();

    record Begin(long partyId) implements PartyEvent {

    }

    record Terminate(long partyId) implements PartyEvent {

    }
}
