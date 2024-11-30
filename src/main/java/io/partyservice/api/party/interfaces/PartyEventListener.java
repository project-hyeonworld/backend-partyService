package io.partyservice.api.party.interfaces;


import io.partyservice.common.event.EventListener;
import io.partyservice.api.party.event.PartyEvent;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 5.
 */
public interface PartyEventListener extends EventListener<PartyEvent> {
  void handlePartyEntityBeginEvent(PartyEvent.Begin event);
  void handlePartyEntityTerminateEvent(PartyEvent.Terminate event);
}
