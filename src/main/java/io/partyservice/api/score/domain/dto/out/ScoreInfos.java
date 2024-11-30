package io.partyservice.api.score.domain.dto.out;

import io.partyservice.api.score.infrastructure.entity.Score;
import java.util.ArrayList;
import java.util.List;
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

    public static ScoreInfos from(List<Score> scores) {
        return new ScoreInfos(scores.stream()
                .map(score -> ScoreInfo.from(score.getPartyId(), score.getUserId(), score.getScore()))
                .toList());
    }

    public Set<Long> getUserIds() {
        return scoreInfos.stream()
                .map(ScoreInfo::getUserId)
                .collect(Collectors.toSet());
    }

    public List<ScoreInfo> getCollection() {
        return scoreInfos;
    }
}
