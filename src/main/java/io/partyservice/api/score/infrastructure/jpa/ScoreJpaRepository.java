package io.partyservice.api.score.infrastructure.jpa;

import io.partyservice.api.score.infrastructure.entity.Score;
import io.partyservice.api.score.infrastructure.entity.ScoreId;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 14.
 */
public interface ScoreJpaRepository extends JpaRepository<Score, ScoreId> {

    @Query("SELECT s FROM Score s WHERE s.partyId = :partyId")
    List<Score> findAllByPartyId(@Param("partyId")long partyId);
}
