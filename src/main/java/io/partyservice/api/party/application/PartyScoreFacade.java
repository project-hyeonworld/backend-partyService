package io.partyservice.api.party.application;

import io.partyservice.api.party.application.port.out.NameScoreDtos;
import io.partyservice.api.party.client.user.UserClient;
import io.partyservice.api.party.client.user.namesResponse;
import io.partyservice.api.score.domain.ScoreService;
import io.partyservice.api.score.domain.dto.out.ScoreInfos;
import io.partyservice.common.annotation.Facade;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 11. 28.
 */
@Facade
@RequiredArgsConstructor
public class PartyScoreFacade {

    private final ScoreService scoreService;
    private final UserClient userClient;

    @Transactional
    public NameScoreDtos ranking(long partyId) {
        ScoreInfos scoreInfos = scoreService.retrieveScores(partyId);
        namesResponse response = userClient.getNamesByIds(scoreInfos.getUserIds());
        return scoreInfos.getNamesScores(response);
    }
}
