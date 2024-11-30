package io.partyservice.api.party.application.port.out;

import java.util.List;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 11. 30.
 */
public record Ranking(
        List<NameScoreDto> nameScoreDtos
) {

    public Ranking(List<NameScoreDto> nameScoreDtos) {
        this.nameScoreDtos = nameScoreDtos.stream()
                .sorted((a, b) -> Long.compare(b.score(), a.score()))
                .toList();
    }

    public static Ranking from(List<NameScoreDto> nameScoreDtos) {
        return new Ranking(nameScoreDtos);
    }
}
