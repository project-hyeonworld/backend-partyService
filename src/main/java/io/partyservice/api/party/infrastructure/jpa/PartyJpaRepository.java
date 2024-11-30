package io.partyservice.api.party.infrastructure.jpa;

import io.partyservice.api.party.infrastructure.entity.PartyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 5.
 */
public interface PartyJpaRepository extends JpaRepository<PartyEntity, Long> {
}
