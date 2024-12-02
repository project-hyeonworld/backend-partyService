package io.partyservice.api.party.controller.dto.res;

import io.partyservice.api.party.domain.dto.out.PartyInfo;
import io.partyservice.common.mapper.CusotmObjectMapper;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 11. 4.
 */
public record PartyBeginResponse(
        long id,
        byte relationType
) implements PartyResponse {

    public static PartyBeginResponse from(PartyInfo partyInfo) {
        return CusotmObjectMapper.convert(partyInfo, PartyBeginResponse.class);
    }
}
