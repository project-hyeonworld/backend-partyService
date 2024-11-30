package io.partyservice.api.score.infrastructure;

import io.partyservice.api.score.infrastructure.entity.Score;
import io.partyservice.api.score.infrastructure.jpa.ScoreJpaRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 14.
 */
@Repository
@Primary
@RequiredArgsConstructor
public class ScoreRepositoryImpl implements ScoreRepository {

    private final ScoreJpaRepository scoreJpaRepository;


    @Override
    public List<Score> saveAll(List<Score> score) {
        return scoreJpaRepository.saveAll(score);
    }

    @Override
    public List<Score> findByPartyId(long partyId) {
        return scoreJpaRepository.findAllByPartyId(partyId);
    }


}
