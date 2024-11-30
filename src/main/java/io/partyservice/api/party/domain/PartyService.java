package io.partyservice.api.party.domain;

import static io.partyservice.api.party.domain.dto.out.PartyInfo.*;

import io.partyservice.api.party.client.user.RelationType;
import io.partyservice.api.party.domain.dto.out.PartyInfo;
import io.partyservice.api.party.infrastructure.PartyRepository;
import io.partyservice.common.exception.ExceptionResponseCode;
import io.partyservice.common.exception.ServerException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 5.
 */
@Service
@RequiredArgsConstructor
public class PartyService {

    private final PartyRepository partyRepository;

    @Transactional(readOnly = true)
    public PartyInfo findById(long partyId) {
        return from(partyRepository.findById(partyId)
                .orElseThrow(() -> new ServerException(ExceptionResponseCode.PARTY_NOT_FOUND)));
    }

    @CacheEvict(cacheNames = "partyId", key = "#relationType")
    public PartyInfo begin(int relationType) {
        return from(partyRepository.save(create(relationType)));
    }

    @Transactional
    public void terminate(long partyId) {
        PartyInfo partyInfo = this.findById(partyId);
        partyRepository.save(partyInfo.entityToTerminate());
    }


    @Transactional(readOnly = true)
    public Long findByRelationType(int relationType) {
        if (RelationType.PLAYABLE_EXCLUDE_HOUSE_HOLD_ORDINAL.contains(relationType)) {
            return retrieveByPlayer(relationType);
        }
        if (relationType == RelationType.HOUSE_HOLD.ordinal()) {
            return retrievePartyEntityIdByHouseHold(relationType);
        }
        if (relationType == RelationType.MODERATOR.ordinal()) {
            return retrievePartyEntityIdByModerator();
        }
        return null;
    }

    @Cacheable(cacheNames = "partyId", key = "#relationType")
    public Long retrieveByPlayer(int relationType) {
        return partyRepository.findIdByRelationType(relationType)
                .orElseGet(() -> null);
    }

    @Cacheable(cacheNames = "partyId", key = "#relationType")
    public Long retrievePartyEntityIdByHouseHold(int relationType) {
        return partyRepository.findIdByHouseHold()
                .orElseGet(() -> null);
    }

    public Long retrievePartyEntityIdByModerator() {
        return partyRepository.findIdByHouseHold()
                .orElseGet(() -> -1L);
    }
}
