package io.partyservice.api.party.controller.dto.res;

import java.util.List;
import io.partyservice.api.party.application.port.out.NameScoreDto;
import io.partyservice.api.party.application.port.out.Ranking;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 11. 30.
 */
public record RankingResponse(
        List<Participant> participants
) {

    record Participant(
            String name,
            Long score
    ) {

        public static Participant from(String userName, long score) {
            if (userName == null || userName.trim().isEmpty()) {
                throw new IllegalArgumentException("User name cannot be null or empty");
            }
            return new Participant(userName, score);
        }
    }

    public static RankingResponse from(Ranking ranking) {
        return new RankingResponse(convertParticipant(ranking.nameScoreDtos()));
    }

    private static List<Participant> convertParticipant(List<NameScoreDto> nameScoreDtos) {
        return nameScoreDtos.stream()
                .map(participant -> Participant.from(participant.name(),
                        participant.score()))
                .toList();
    }

}
