package io.partyservice.api.score.infarstructure.jpa;

import io.partyservice.api.score.infarstructure.entity.ScoreHistory;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 14.
 */
public interface ScoreHistoryJpaRepository extends JpaRepository<ScoreHistory, Long> {

}
