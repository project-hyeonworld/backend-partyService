package io.partyservice.api.score.domain;

import io.partyservice.api.score.domain.dto.out.ScoreInfo;
import io.partyservice.api.score.domain.dto.out.ScoreInfos;
import io.partyservice.api.score.infrastructure.ScoreRepository;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 14.
 */
@Service
@RequiredArgsConstructor
public class ScoreService {
  private final ScoreRepository scoreRepository;

  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public void save(long partyId, Map<Long, Long> userSumScore) {
      scoreRepository.saveAll(ScoreInfo.from(partyId, userSumScore));
  }

  public ScoreInfos retrieveScores(long partyId) {
    return ScoreInfos.from(scoreRepository.findByPartyId(partyId));
  }

  //TODO : connect ScoreHistory
//  public void save(long partyId, ScoreHistoryInfos scoreHistoryInfos) {
//    scoreRepository.saveAll(ScoreInfo.createEntities(partyId, scoreHistoryInfos));
//  }

}
