package io.partyservice.api.score.infrastructure;

import io.partyservice.api.score.infrastructure.entity.Score;
import java.util.List;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 14.
 */
public interface ScoreRepository {
  List<Score> saveAll (List<Score> score);

  List<Score> findByPartyId(long partyId);
}
