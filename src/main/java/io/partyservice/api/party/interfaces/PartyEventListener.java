package io.partyservice.api.party.interfaces;


import io.partyservice.api.party.event.PartyBeginEvent;
import io.partyservice.api.party.event.PartyTerminateEvent;
import io.partyservice.common.event.EventListener;
import io.partyservice.api.party.event.PartyEvent;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 5.
 */
public interface PartyEventListener extends EventListener<PartyEvent> {
}
