package io.partyservice.api.party.application.port.out;

import java.util.List;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 11. 30.
 */
public record NameScoreDtos (
        List<NameScoreDto> nameScoreDtos
){
    public static NameScoreDtos from(List<NameScoreDto> nameScoreDtos) {
        return new NameScoreDtos(nameScoreDtos);
    }
}
