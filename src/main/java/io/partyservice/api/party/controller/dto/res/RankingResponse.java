package io.partyservice.api.party.controller.dto.res;

import io.partyservice.api.party.application.port.out.NameScoreDtos;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 11. 30.
 */
public class RankingResponse {

    public static RankingResponse from(NameScoreDtos ranking) {
        return new RankingResponse();
    }
}
