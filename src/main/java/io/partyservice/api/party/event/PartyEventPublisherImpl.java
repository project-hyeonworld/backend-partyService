package io.partyservice.api.party.event;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 5.
 */
@Component
@RequiredArgsConstructor
public class PartyEventPublisherImpl implements PartyEventPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void execute(PartyEvent event) {
        applicationEventPublisher.publishEvent(event);
    }
}
