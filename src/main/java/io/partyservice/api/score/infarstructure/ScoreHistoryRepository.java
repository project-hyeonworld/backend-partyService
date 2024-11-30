package io.partyservice.api.score.infarstructure;

import io.partyservice.api.score.infarstructure.entity.ScoreHistory;
import java.util.List;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 11. 12.
 */
public interface ScoreHistoryRepository {
    ScoreHistory save(ScoreHistory scoreHistory);

    List<ScoreHistory> saveAll(List<ScoreHistory> scoreHistory);

    List<ScoreHistory> findByPartyId(long partyId);
}
