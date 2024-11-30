package io.partyservice.api.party.infrastructure;

import io.partyservice.api.party.infrastructure.entity.Party;
import java.util.Optional;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 5.
 */
public interface PartyRepository {

    Party save(Party party);

    Optional<Party> findById(long partyId);

    Optional<Long> findIdByRelationType(int relationType);

    void terminateAll();

    Optional<Long> findIdByHouseHold();
}
