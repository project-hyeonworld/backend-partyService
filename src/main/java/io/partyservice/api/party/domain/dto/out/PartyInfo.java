package io.partyservice.api.party.domain.dto.out;

import io.partyservice.common.mapper.CusotmObjectMapper;
import java.time.LocalDateTime;
import io.partyservice.api.party.infrastructure.entity.Party;
import lombok.Getter;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 5.
 */
@Getter
public class PartyInfo {

    long id;
    int relationType;

    public static PartyInfo from(io.partyservice.api.party.infrastructure.entity.Party party) {
        return CusotmObjectMapper.convert(party, PartyInfo.class);
    }

    private static Party.PartyBuilder initializeEntity() {
        return Party.builder();
    }

    public static Party create(int relationType) {
        return initializeEntity()
                .relationType(relationType)
                .build();
    }

    public Party entityToTerminate() {
        return initializeEntity()
                .id(id)
                .terminatedAt(LocalDateTime.now())
                .build();
    }

}
