package io.partyservice.api.score.domain.dto.out;

import io.partyservice.api.score.infrastructure.entity.Score;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.Getter;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 18.
 */
public class ScoreInfo {
  private final long partyId;

  @Getter
  private final long userId;
  @Getter
  private final long score;

  private ScoreInfo(long partyId, long userId, long score) {
      this.partyId = partyId;
      this.userId = userId;
      this.score = score;
  }

  public static Score createEntity(long partyId, long userId, long score) {
    return io.partyservice.api.score.infrastructure.entity.Score.from(partyId, userId, score);
  }

  public static ScoreInfo from(long partyId, long userId, long score) {
    return new ScoreInfo(partyId, userId, score);
  }

  public static List<Score> from(long partyId, Map<Long, Long> userSumScore) {
    return userSumScore.entrySet().stream()
        .map(entry -> ScoreInfo.createEntity(partyId, entry.getKey(), entry.getValue()))
        .collect(Collectors.toList());
  }
}
