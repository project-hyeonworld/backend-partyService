package io.partyservice.api.party.controller.dto.res;

import io.partyservice.api.party.domain.dto.out.Party;
import io.partyservice.common.mapper.ObjectrMapper;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 11. 4.
 */
public record PartyBeginResponse(
        long id,
        byte relationType
) implements PartyResponse {

    public static PartyBeginResponse from(Party party) {
        return ObjectrMapper.convert(party, PartyBeginResponse.class);
    }
}
