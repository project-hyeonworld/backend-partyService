package io.partyservice.api.score.domain.dto.out;

import io.partyservice.api.party.application.port.out.NameScoreDto;
import io.partyservice.api.party.application.port.out.Ranking;
import io.partyservice.api.score.infrastructure.entity.Score;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 11. 28.
 */
public class ScoreInfos {
    private final List<ScoreInfo> scoreInfos;

    private ScoreInfos(List<ScoreInfo> scoreInfos) {
        this.scoreInfos = new ArrayList<>(scoreInfos);
    }

    public static ScoreInfos from(List<Score> scoreEntities) {
        return new ScoreInfos(scoreEntities.stream()
                .map(score -> ScoreInfo.from(score.getPartyId(), score.getUserId(), score.getScore()))
                .toList());
    }

    public Set<Long> getUserIds() {
        return scoreInfos.stream()
                .map(ScoreInfo::getUserId)
                .collect(Collectors.toSet());
    }

    public Ranking getNamesScores(Map<Long, String> userIdNames) {
        return Ranking.from(scoreInfos.stream()
                .map(scoreInfo -> NameScoreDto.from(userIdNames.get(scoreInfo.getUserId()), scoreInfo.getScore()))
                .toList());
    }
}
