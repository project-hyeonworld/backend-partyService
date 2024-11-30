package io.partyservice.api.party.application.port.out;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 11. 30.
 */
public record NameScoreDto(
        String name,
        long score
){

    public NameScoreDto(String name, long score) {
        this.name = name;
        this.score = score;
    }

    public static NameScoreDto from (String name, long score) {
        return new NameScoreDto(name, score);
    }
}

