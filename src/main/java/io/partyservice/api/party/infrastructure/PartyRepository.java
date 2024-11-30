package io.partyservice.api.party.infrastructure;

import io.partyservice.api.party.infrastructure.entity.PartyEntity;
import java.util.Optional;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 5.
 */
public interface PartyRepository {

    PartyEntity save(PartyEntity party);

    Optional<PartyEntity> findById(long partyId);

    Optional<Long> findIdByRelationType(int relationType);

    void terminateAll();

    Optional<Long> findIdByHouseHold();
}
