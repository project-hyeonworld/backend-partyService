package io.partyservice.api.score.infarstructure;

import io.partyservice.api.score.infarstructure.entity.Score;
import java.util.List;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 14.
 */
public interface ScoreRepository {
  List<Score> saveAll (List<Score> score);



}
