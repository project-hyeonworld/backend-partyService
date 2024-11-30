package io.partyservice.api.score.infrastructure.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 14.
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@IdClass(ScoreId.class)
public class Score {

  @Id
  @Column(name = "party_id")
  private Long partyId;

  @Id
  @Column(name = "user_id")
  private Long userId;

  private Long score;

  public static ScoreBuilder defaultBuilder(){
    return Score.builder();
  }

  public static Score from(long partyId, long userId, long score) {
    return defaultBuilder()
        .partyId(partyId)
        .userId(userId)
        .score(score)
        .build();
  }
}