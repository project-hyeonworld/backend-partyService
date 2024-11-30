package io.partyservice.api.party.infrastructure;

import io.partyservice.api.party.infrastructure.entity.Party;
import io.partyservice.api.party.infrastructure.jdbc.PartyJdbcTemplateRepository;
import io.partyservice.api.party.infrastructure.jpa.PartyJpaRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 5.
 */
@Repository
@Primary
@RequiredArgsConstructor
public class PartyRepositoryImpl implements PartyRepository {
  private final PartyJpaRepository partyJpaRepository;
  private final PartyJdbcTemplateRepository partyJdbcTemplateRepository;

  @Override
  public Party save(Party party) {
    return partyJdbcTemplateRepository.save(party);
  }

  @Override
  public Optional<Party> findById(long partyId) {
    return partyJpaRepository.findById(partyId);
  }

  @Override
  public Optional<Long> findIdByRelationType(int relationType) {
    return partyJdbcTemplateRepository.findIdByRelationType(relationType);
  }

  @Override
  public void terminateAll() {
    partyJdbcTemplateRepository.terminateAll();
  }

  @Override
  public Optional<Long> findIdByHouseHold() {
    return partyJdbcTemplateRepository.findIdByHouseHold();
  }
}
