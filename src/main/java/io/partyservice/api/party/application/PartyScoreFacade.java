package io.partyservice.api.party.application;

import io.partyservice.api.party.application.port.out.Ranking;
import io.partyservice.api.party.client.user.UserClient;
import io.partyservice.api.score.domain.ScoreService;
import io.partyservice.api.score.domain.dto.out.ScoreInfos;
import io.partyservice.common.annotation.Facade;
import java.util.Map;
import java.util.stream.Collectors;
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
    public Ranking createRankingTable(long partyId) {
        ScoreInfos scoreInfos = scoreService.retrieveScores(partyId);
        Map<Long, String> userIdNames = scoreInfos.getUserIds().stream()
                .collect(Collectors.toMap(
                        userId -> userId,
                        userClient::getNameById
                ));
        return scoreInfos.getNamesScores(userIdNames);
    }
}
