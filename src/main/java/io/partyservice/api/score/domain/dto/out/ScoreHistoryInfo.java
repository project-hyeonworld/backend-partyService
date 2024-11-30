package io.partyservice.api.score.domain.dto.out;

import io.partyservice.api.round.domain.dto.in.RoundPlayCommand;
import io.partyservice.api.round.domain.dto.in.RoundResultConfirmCommand;
import io.partyservice.api.scoreHistory.infrastructure.entity.ScoreHistory;
import io.partyservice.api.user.application.dto.ScoreDto;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 14.
 */
public class ScoreHistoryInfo {
  Long userId;
  Long partyId;
  Long roundId;
  String answer;
  Long score;

  public static ScoreHistory createEntity(RoundPlayCommand command, long roundId, String answer) {
    return ScoreHistory.builder()
        .partyId(command.partyId())
        .roundId(roundId)
        .userId(command.userId())
        .answer(command.answer())
        .score(answer.equals(command.answer())? 1L : 0L)
        .build();
  }

  public static List<ScoreHistory> createEntities(long partyId, RoundResultConfirmCommand command) {
    List<ScoreHistory> scoreHistories = new ArrayList<>();
    for (ScoreDto participant : command.userScoreDtos()) {
      ScoreHistory scoreHistory = ScoreHistory.builder()
          .partyId(partyId)
          .roundId(command.roundId())
          .userId(participant.userId())
          .score(participant.score())
          .build();
      scoreHistories.add(scoreHistory);
    }

    return scoreHistories;
  }
}