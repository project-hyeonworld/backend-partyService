package io.partyservice.api.party.domain.dto.out;

import io.partyservice.api.party.infrastructure.entity.PartyEntity;
import io.partyservice.common.mapper.ObjectrMapper;
import java.time.LocalDateTime;
import lombok.Getter;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 5.
 */
@Getter
public class Party {

    long id;
    int relationType;

    public static Party from(PartyEntity party) {
        return ObjectrMapper.convert(party, Party.class);
    }

    private static PartyEntity.PartyEntityBuilder initializeEntity() {
        return PartyEntity.builder();
    }

    public static PartyEntity create(int relationType) {
        return initializeEntity()
                .relationType(relationType)
                .build();
    }

    public PartyEntity entityToTerminate() {
        return initializeEntity()
                .id(id)
                .terminatedAt(LocalDateTime.now())
                .build();
    }

}
